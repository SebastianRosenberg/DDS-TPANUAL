package tpanual.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;
import tpanual.main.direccion.Pais;
import tpanual.main.direccion.Provincia;
import tpanual.main.poi.PalabraClave;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.HibernateSession;

public class MainHibernate {

	public static void main(String[] args) {
		
		HibernateSession h = new HibernateSession();
		Direccion d=new Direccion.DireccionBuilder().callePrincipal("Hidalgo").numero("0643").entreCalle1("Rojas").crearDireccion();
		d.setLocalidad(Localidad.getLocalidadAuxiliar());
		h.add(d);
		
		
		List<String> l = new ArrayList<String>();
		l.add("Una palabra");
		l.add("Dos palabra");
		List<Servicio> ssss = new ArrayList<Servicio>();
		ssss.add(new Servicio("Servicio1"));
		ssss.add(new Servicio("Servicio2"));
		RubroFW fw = RubroFWFactory.getRubro("Chinelas", 20);
		
		PuntoDeInteres poi = PuntoDeInteresFactory.getParadaDeColectivo(220, 220, "Linea 643", d, l, "643");
		PuntoDeInteres poi2 = PuntoDeInteresFactory.getCGP(220, 150, "CGP", d, l, ssss, 2);
		PuntoDeInteres poi3 = PuntoDeInteresFactory.getLocalComercial(205, 130, "LocalComercial", d, l, fw, null);
		PuntoDeInteres poi4 = PuntoDeInteresFactory.getSucursal(130, 54241, "Sucursal", d, l, ssss);
		h.add(poi);
		h.add(poi2);
		h.add(poi3);
		h.add(poi4);
		
		
		h.close();
	}
}
