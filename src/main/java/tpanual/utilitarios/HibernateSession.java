package tpanual.utilitarios;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tpanual.Rubro.RubroConcreteFW;
import tpanual.Rubro.RubroFW;
import tpanual.main.MainHibernate;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;
import tpanual.main.direccion.Pais;
import tpanual.main.direccion.Provincia;
import tpanual.main.poi.CGP;
import tpanual.main.poi.LocalComercial;
import tpanual.main.poi.PalabraClave;
import tpanual.main.poi.ParadaColectivo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.main.poi.SucursalBanco;
import tpanual.main.poi.TipoPuntoInteres;

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
		configuration.configure().addAnnotatedClass(PuntoDeInteres.class);
		configuration.configure().addAnnotatedClass(TipoPuntoInteres.class);
		configuration.configure().addAnnotatedClass(ParadaColectivo.class);
		configuration.configure().addAnnotatedClass(CGP.class);
		configuration.configure().addAnnotatedClass(LocalComercial.class);
		configuration.configure().addAnnotatedClass(SucursalBanco.class);
		configuration.configure().addAnnotatedClass(PalabraClave.class);
		configuration.configure().addAnnotatedClass(Servicio.class);
		configuration.configure().addAnnotatedClass(RubroConcreteFW.class);
		configuration.configure().addAnnotatedClass(RubroFW.class);
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
	
	public int add(PuntoDeInteres poi){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer empIdSaved = null;
		try {
			tx = session.beginTransaction();
			empIdSaved = (Integer) session.save(poi);
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
