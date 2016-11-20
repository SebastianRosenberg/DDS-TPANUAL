package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDePoi;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.HibernateFactorySessions;

public class Hibernate_PuntoDeInteresTest {
	
	static AdministradorDePoi adp = new AdministradorDePoi();
	static Integer idPoiModificado;
	static Integer idPoiEliminado;
	static HibernateFactorySessions hs;
	
	@BeforeClass
	public static void setUp(){
		
		Direccion direccion1=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		Direccion direccion2=new Direccion.DireccionBuilder().callePrincipal("Mitre").numero("1354").barrio("Congreso").crearDireccion();
		
		List<String> palabras1=new ArrayList<String>();
		palabras1.add("CGP");
		palabras1.add("Zona peligrosa");
		
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("Zona Segura");
		
		List<Servicio> servicios1=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		List<Servicio> servicios2=Servicio.getListaServicios("Jubilaciones");

		servicios1.get(0).setHorario(getHorario1());
		servicios1.get(1).setHorario(getHorario2());
		servicios1.get(2).setHorario(getHorario3());
		servicios2.get(0).setHorario(getHorario3());
		
		//Persisto el punto de interes
		PuntoDeInteres pdi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1 Hibernate POI TEST", direccion1, palabras1, servicios1, 25);
		PuntoDeInteres pdi2=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 2 Hibernate POI TEST", direccion2, palabras2, servicios2, 25);
		PuntoDeInteres pdi3=PuntoDeInteresFactory.getSucursal(2500D, 3200D, "Galicia", direccion1, palabras1, servicios1);
		
		idPoiModificado = pdi.getId();
		idPoiEliminado = pdi2.getId();
		hs = new HibernateFactorySessions();
		hs.add(pdi);
		hs.add(pdi2);
		hs.add(pdi3);
			
	}
	
	private static HorarioDeAtencion getHorario1(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia=1;dia<6;dia++) {
			horario.addRangoDia(800, 1700, dia);
		}
		return horario;
	}

	private static HorarioDeAtencion getHorario2(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia=1;dia<7;dia++) {
			horario.addRangoDia(1000, 1600, dia);
		}
		return horario;
	}
	
	private static HorarioDeAtencion getHorario3(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia=2;dia<6;dia++) {
			horario.addRangoDia(1200, 2000, dia);
		}
		return horario;
	}
	
	@Test
	public void modificarPoiTest(){
		
		PuntoDeInteres p = hs.obtenerPoi(idPoiModificado);
		
		//Compruebo la latitud y longitud con la cual fue ingresado
		assertTrue(p.getLatitud() == 2500D && p.getLongitud() == 3200D);
		
		//Modifico los valores de latitud y longitud
		p.setLatitud(666D); p.setLongitud(444D);
		
		//Actualizo
		hs.modificarPuntoDeInteres(p);
		
		//Obtengo el modificado
		PuntoDeInteres pModificado = hs.obtenerPoi(idPoiModificado);
		
		//Compruebo que el valor haya cambiado
		assertTrue(pModificado.getLatitud() == 666D && pModificado.getLongitud() == 444D);
	}
	
	@Test
	public void eliminarPoiTest(){
		
		//Compruebo que exista
		PuntoDeInteres p = hs.obtenerPoi(idPoiEliminado);

		//Elimino
		hs.eliminarPuntoDeInteres(p);

		//Compruebo que al buscarlo no exista
		PuntoDeInteres ppp = hs.obtenerPoi(idPoiEliminado);
		assertNull(ppp);
	}	
}
