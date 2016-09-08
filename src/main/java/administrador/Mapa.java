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
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.Utilitarios;

public class Mapa {
	
	private Map<Integer, PuntoDeInteres> puntos;
	private List<AdaptadorServicioExterno> adaptadores;
	
	
//	//recordar linea 124, ubicacion de esta estructura y metodos
//	private Hashtable<String, String> hashAdmins;

    private static Mapa instance;
	
	public static Mapa getInstance(){
		if (instance==null)
			instance=new Mapa();
		return instance;
	}
	
	private Mapa() {
		puntos=new HashMap<Integer, PuntoDeInteres>();
		adaptadores=new ArrayList<AdaptadorServicioExterno>();
		adaptadores.add(new AdaptadorServicioExternoCGP());
	}
	
	void agregarPunto(PuntoDeInteres punto){
		puntos.put(punto.getId(),  punto);
	}
	
	PuntoDeInteres eliminarPunto(Integer id){
		
		return puntos.remove(id);
		
	}
	

	
	/**
	 * Recibe un texto libre, busca en los puntos de interes almacenados en el mapa, aquellos que cumplan coincidencia con el texto y los devuelve.
	 * @param x: String a buscar
	 * @test: false para buscar en servicios externos, true para buscar en mock
	 */
	List<PuntoDeInteres> buscarPuntosDeInteresEnMemoria(String x){
		List<PuntoDeInteres> listaADevolver=new ArrayList<PuntoDeInteres>();
		Iterator<PuntoDeInteres> it=puntos.values().iterator();
		while (it.hasNext()){
			PuntoDeInteres punto=it.next();
			if (punto.buscarCoincidencia(x)) listaADevolver.add(punto);
		}
		return listaADevolver;
	}
	
	
	/**
	 * Obtiene un PuntoDeInteres accediendo solo por id sin buscar
	 * @param id
	 * @return: null si no encuentra ninguno
	 */
	
	PuntoDeInteres obtenerPuntoDeInteres(Integer id){
		
		return puntos.get(id);
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
				List<PuntoDeInteres> ll=adaptador.buscar(x);
				if (ll!=null)
					lista=Utilitarios.fusionarListasSinRepetidos(lista, ll);
			}else{
				//llamar al mock
			}
		}
		agregarAMemoria(lista);
		return lista;
	}
	
	/**
	 * Agrega a la lista de POI en memoria la lista que recibe por parámetro
	 * @param lista
	 */
	
	private void agregarAMemoria(List<PuntoDeInteres> lista){
		if (lista!=null){
			Iterator<PuntoDeInteres> i=lista.iterator();
			while (i.hasNext()){
				agregarPunto(i.next());
			}
		}
	}
	
	List<PuntoDeInteres> buscarBancos(String banco, String servicio){
		AdaptadorServicioExternoBancos adaptador=new AdaptadorServicioExternoBancos();
		List<PuntoDeInteres> l= adaptador.buscar(banco, servicio);
		agregarAMemoria(l);
		return l;
	}
	
////pongo la tabla de usuarios aca, luego va a ser movida
//	private void agregarAdministrador(String name, String pass)
//	{
//		hashAdmins.put(name, pass);
//	}
//	
//	private
//	
//	
}
