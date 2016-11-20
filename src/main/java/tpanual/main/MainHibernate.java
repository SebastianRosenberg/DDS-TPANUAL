package tpanual.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;
import tpanual.utilitarios.HibernateFactorySessions;

public class MainHibernate {

	public static void main(String[] args) {
		
		HibernateFactorySessions h = new HibernateFactorySessions();
		Direccion d=new Direccion.DireccionBuilder().barrio("barrio").callePrincipal("localidad").departamento("departamento").crearDireccion();
		List<String> l = new ArrayList<String> ();
		l.add("palabraClave1");
		
		PuntoDeInteres poi = PuntoDeInteresFactory.getSucursal(200, 300, "nombre sucursal", d, l, Servicio.getListaServicios("vende falopa"));
		
		//h.add(poi);
		
		System.out.println("buscando...");
		
		List<PuntoDeInteres> lis = h.obtenerPoiPorQuery("sucursal");
		
		System.out.println("mostrando...");
		
		Iterator<PuntoDeInteres> it = lis.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		
		System.exit(0);
		
		
	}
}
