package tpanual.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;
import tpanual.main.direccion.Pais;
import tpanual.main.direccion.Provincia;
import tpanual.utilitarios.HibernateSession;

public class MainHibernate {

	public static void main(String[] args) {
		
		HibernateSession h = new HibernateSession();
		Direccion d=new Direccion.DireccionBuilder().callePrincipal("Hidalgo").numero("0643").entreCalle1("Rojas").crearDireccion();
		d.setLocalidad(Localidad.getLocalidadAuxiliar());
		h.add(d);
		h.close();
	}
}
