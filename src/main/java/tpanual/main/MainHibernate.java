package tpanual.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainHibernate {
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Direccion.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		
		factory = configuration.buildSessionFactory(serviceRegistry);
		MainHibernate testWorker = new MainHibernate();
		testWorker.addDireccion("CallePrincipal");
		testWorker.addDireccion("CallePrincipal2");
		factory.close();
	}

	private int addDireccion(String callePrincipal) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer empIdSaved = null;
		try {
			tx = session.beginTransaction();
			Direccion d=new Direccion.DireccionBuilder().callePrincipal(callePrincipal).crearDireccion();
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
