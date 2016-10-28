package tpanual.utilitarios;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tpanual.main.MainHibernate;
import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;
import tpanual.main.direccion.Pais;
import tpanual.main.direccion.Provincia;

public class HibernateSession {
	
	private Configuration configuration;
	private SessionFactory factory;
	private ServiceRegistry serviceRegistry;	
	
	public HibernateSession(){
	
		configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Direccion.class);
		configuration.configure().addAnnotatedClass(Localidad.class);
		configuration.configure().addAnnotatedClass(Provincia.class);
		configuration.configure().addAnnotatedClass(Pais.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		
		factory = configuration.buildSessionFactory(serviceRegistry);

	}
	
	public void close(){
		factory.close();
	}
	
	public int add(Direccion d){
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
