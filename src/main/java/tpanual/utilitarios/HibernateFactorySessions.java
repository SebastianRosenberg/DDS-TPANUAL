package tpanual.utilitarios;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import administrador.Busqueda;
import tpanual.Rubro.RubroConcreteFW;
import tpanual.Rubro.RubroFW;
import tpanual.main.HorarioDeAtencion;
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
import tpanual.usuario.Administrador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;

public class HibernateFactorySessions {

	private Configuration configuration;
	private SessionFactory factory;
	private ServiceRegistry serviceRegistry;

	public HibernateFactorySessions() {

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
		configuration.configure().addAnnotatedClass(Busqueda.class);
		configuration.configure().addAnnotatedClass(Usuario.class);
		configuration.configure().addAnnotatedClass(Terminal.class);
		configuration.configure().addAnnotatedClass(Administrador.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

		factory = configuration.buildSessionFactory(serviceRegistry);

	}

	public void close() {
		factory.close();
	}

	public int add(Direccion d) {
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

	public int add(PuntoDeInteres poi) {
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

	public PuntoDeInteres obtenerPoi(Integer id) {
		Session session = null;
		PuntoDeInteres poi = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			poi = (PuntoDeInteres) session.get(PuntoDeInteres.class, id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return poi;
	}

	public void modificarPuntoDeInteres(PuntoDeInteres pdi) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(pdi);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void eliminarPuntoDeInteres(PuntoDeInteres pdi) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(pdi);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// Agrego un m�todo que recibe un objeto por parametro y lo persiste

	public int add(Usuario d) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer usrIdSaved = null;
		try {
			tx = session.beginTransaction();
			usrIdSaved = (Integer) session.save(d);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return usrIdSaved;
	}

	public void eliminarObjetoBd(Object obj) {

		Session session = factory.openSession();

		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public Usuario obtenerUsuario(Integer id) {
		Session session = null;
		// PuntoDeInteres poi = null;
		Usuario usr = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			usr = (Usuario) session.get(Usuario.class, id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return usr;
	}

	public int add(Busqueda bus) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer busIdSaved = null;
		try {
			tx = session.beginTransaction();
			busIdSaved = (Integer) session.save(bus);
			tx.commit();
		} catch (Throwable e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return busIdSaved;
	}

	public Busqueda obtenerBusqueda(Integer id) {
		Session session = null;
		Busqueda bus = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			bus = (Busqueda) session.get(Busqueda.class, id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return bus;
	}

	public void modificarUsuario(Usuario usr) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(usr);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<PuntoDeInteres> obtenerTodosLosPuntos(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<PuntoDeInteres> pois = session.createCriteria(PuntoDeInteres.class).list();
			tx.commit();
			return pois;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}		
	}
	
	public List<Usuario> obtenerTodosLosUsuarios(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Usuario> usuarios = session.createCriteria(Usuario.class).list();
			tx.commit();
			return usuarios;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}		
	}	
}
