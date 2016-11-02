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
	static HibernateFactorySessions hs;
	
	@BeforeClass
	public static void setUp(){
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("CGP");
		palabras.add("Zona peligrosa");
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");

		servicios.get(0).setHorario(getHorario1());
		servicios.get(1).setHorario(getHorario2());
		servicios.get(2).setHorario(getHorario3());
		
		//Persisto el punto de interes
		PuntoDeInteres pdi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1 Hibernate POI TEST", direccion, palabras, servicios, 25);
		idPoiModificado = pdi.getId();
		hs = new HibernateFactorySessions();
		hs.add(pdi);
			
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
		assertTrue(p.getLatitud() == 666D && p.getLongitud() == 444D);
	}
	
}
