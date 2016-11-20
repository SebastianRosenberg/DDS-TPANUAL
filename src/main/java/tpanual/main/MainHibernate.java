package tpanual.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		
		PuntoDeInteres poi = PuntoDeInteresFactory.getCGP(300, 500, "nombre", d, l, Servicio.getListaServicios("servicio1"), 90);
		
		//h.add(poi);
		
		List<PuntoDeInteres> lis = h.obtenerPoiPorQuery("palabraClave1");
		
		Iterator<PuntoDeInteres> it = lis.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		
		System.exit(0);
		
		
	}
}
