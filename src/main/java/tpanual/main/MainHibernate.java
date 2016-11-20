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
		
		PuntoDeInteres poi = PuntoDeInteresFactory.getCGP(900, 800, "Boedo cgp", d, l, Servicio.getListaServicios("venta de estupefacientes d"), 90);
		
		//h.add(poi);
		
		System.out.println("buscando...");
		
		List<PuntoDeInteres> lis = h.obtenerPoiPorQuery("kiosko");
		
		System.out.println("mostrando...");
		
		Iterator<PuntoDeInteres> it = lis.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		
		System.exit(0);
		
		
	}
}
