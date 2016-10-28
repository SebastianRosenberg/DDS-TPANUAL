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

public class MainHibernate {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Direccion.class);
		configuration.configure().addAnnotatedClass(Localidad.class);
		configuration.configure().addAnnotatedClass(Provincia.class);
		configuration.configure().addAnnotatedClass(Pais.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		
		factory = configuration.buildSessionFactory(serviceRegistry);
		MainHibernate testWorker = new MainHibernate();
		Direccion d=new Direccion.DireccionBuilder().callePrincipal("Hidalgo").numero("0643").entreCalle1("Rojas").crearDireccion();
		d.setLocalidad(Localidad.getLocalidadAuxiliar());
		testWorker.addDireccion(d);
		factory.close();
	}

	private int addDireccion(Direccion d) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer empIdSaved = null;
		try {
			tx = session.beginTransaction();
			empIdSaved = (Integer) session.save(d);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return empIdSaved;
	}	


}
