package tpanual.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDePoi;
import administrador.Mapa;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Dias;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.rubro.RubroFW;
import tpanual.rubro.RubroFWFactory;

public class AdministradorDePoiTest {
	
	HorarioDeAtencion horario;

	@BeforeClass
	public static void setUp(){
		
		AdministradorDePoi puntoAdminSetUp = AdministradorDePoi.getInstance();
		
		HorarioDeAtencion horario=new HorarioDeAtencion();
		HorarioDeAtencion horario2=new HorarioDeAtencion();
		for (int dia=1;dia<8;dia++){ //Agrega el horario de atencion lunes a domingo de 9:00 a 14:00
			horario.addRangoDia(1700, 2030, dia);// y de 17:00 a 20:30
			horario.addRangoDia(900, 1400, dia);
			horario2.addRangoDia(900, 1400, dia);
		}
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		Direccion direccion2=new Direccion.DireccionBuilder().callePrincipal("Zarraga").numero("545").barrio("Once").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("CGP");
		palabras2.add("Zona peligrosa");
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		List<Servicio> servicios2=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");

		servicios.get(0).setHorario(horario);
		servicios.get(1).setHorario(horario);
		
		servicios2.get(0).setHorario(horario2);
		servicios2.get(1).setHorario(horario2);
		
		PuntoDeInteres pdi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1 AGREGAR", direccion, palabras2, servicios, 25);
		PuntoDeInteres pdi2=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna ELIMINAR", direccion2, palabras, servicios2, 25);
				
		puntoAdminSetUp.agregarPoi(pdi);
		puntoAdminSetUp.agregarPoi(pdi2);
	}


	
	
	@Test
	public void agregarPoiTest(){
		
		AdministradorDePoi administrador = AdministradorDePoi.getInstance();
			
		List<PuntoDeInteres> lista=administrador.busquedaDePuntosDeInteres("");
		
		
		Iterator<PuntoDeInteres> i = lista.iterator();
		boolean aparicion1=false;
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("Sucursal 42")) aparicion1=true;
		}
		
		assertFalse(aparicion1);
		
		lista.clear();
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		List<Servicio> servicios2=Servicio.getListaServicios("Venta de chicles", "Asesoramiento legal");
		PuntoDeInteres pdiAAgregar=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 42", direccion, palabras, servicios2);
		
		administrador.agregarPoi(pdiAAgregar);
		
		lista=administrador.busquedaDePuntosDeInteres("Sucursal 42");
		
		Iterator<PuntoDeInteres> j = lista.iterator();
		aparicion1=false;
		while(j.hasNext()){	
			PuntoDeInteres n=j.next();
			if (n.getNombre().equals("Sucursal 42")) aparicion1=true;
		}
		
		assertTrue(aparicion1);
	
	}
		


	@Test
	public void modificarPoiTest(){
		
		AdministradorDePoi administrador = AdministradorDePoi.getInstance();
		List<PuntoDeInteres> lista=administrador.busquedaDePuntosDeInteres("GCP Comuna 1 AGREGAR");
		
		PuntoDeInteres poi = lista.get(0);
		
		assertTrue(poi.getLatitud() == 2500D);
			
		poi.setLatitud(3500D);;
		administrador.modificarPoi(poi);
		
		List<PuntoDeInteres> lista2=administrador.busquedaDePuntosDeInteres("GCP Comuna 1 AGREGAR");
		
		assertTrue(lista2.get(0).getLatitud() == 3500D);
	
	}
	@Test
	public void eliminarPoiTest(){
		AdministradorDePoi administrador = AdministradorDePoi.getInstance();
		
		List<PuntoDeInteres> lista= administrador.busquedaDePuntosDeInteres("GCP Comuna ELIMINAR");

		
		Iterator<PuntoDeInteres> i = lista.iterator();
		boolean aparicion1=false;
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("GCP Comuna ELIMINAR")) aparicion1=true;
		}
		
		assertTrue(aparicion1);
		
		lista.clear();
		
		lista=administrador.busquedaDePuntosDeInteres("GCP Comuna ELIMINAR");
		
		i = lista.iterator();
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			administrador.eliminarPoi(n); 
		}
		
		lista.clear();
		lista=administrador.busquedaDePuntosDeInteres("GCP Comuna ELIMINAR");
		
		assertTrue(lista.size()==0);
		
	}
	

}
