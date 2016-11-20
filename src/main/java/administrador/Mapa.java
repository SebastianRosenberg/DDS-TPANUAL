package administrador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import administrador.adaptadores.AdaptadorServicioExterno;
import administrador.adaptadores.AdaptadorServicioExternoBancos;
import administrador.adaptadores.AdaptadorServicioExternoCGP;
import tpanual.factory.InterfacesExternasFactory;
import tpanual.main.Dias;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.Utilitarios;

public class Mapa {
	
	private List<AdaptadorServicioExterno> adaptadores;
    private static Mapa instance;
	
	public static Mapa getInstance(){
		if (instance==null)
			instance=new Mapa();
		return instance;
	}
	
	/**
	 * Carga inicial del mapa desde la base de datos
	 */
	
	private Mapa() {
		adaptadores=new ArrayList<AdaptadorServicioExterno>();
		adaptadores.add(new AdaptadorServicioExternoCGP());
		adaptadores.add(new AdaptadorServicioExternoBancos());
	}

	/**
	 * Recibe un texto libre, busca en los puntos de interes almacenados en la base de hibernate
	 * , aquellos que cumplan coincidencia con el texto y los devuelve.
	 * @param x: String a buscar
	 * @test: false para buscar en servicios externos, true para buscar en mock
	 */
	List<PuntoDeInteres> buscarPuntosDeInteresEnHibernate(String x){
		return Utilitarios.getHibernateFactorySessions().obtenerPoiPorQuery(x);
	}
	
	/**
	 * Busca en las todas las fuentes de POI externas consultando la lista de adaptadores de fuentes externas.
	 * @param x
	 * @test: si es true busca en el Mock
	 * @return
	 */
	
	List<PuntoDeInteres> buscarEnFuentesExternas(String x, boolean test){
		Iterator<AdaptadorServicioExterno> i=adaptadores.iterator();
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		while (i.hasNext()){
			AdaptadorServicioExterno adaptador=i.next();
			if (!test){
				List<PuntoDeInteres> ll=adaptador.buscar(x, null);
				if (ll!=null)
					lista=Utilitarios.fusionarListasSinRepetidos(lista, ll);
			}else{
				//NO UTILIZA LOS SERVICIOS EXTERNOS
			}
		}
		return lista;
	}
	
	
	List<PuntoDeInteres> buscarBancos(String banco, String servicio){
		AdaptadorServicioExternoBancos adaptador=new AdaptadorServicioExternoBancos();
		List<PuntoDeInteres> l= adaptador.buscar(banco, servicio);
		return l;
	}

	

}
