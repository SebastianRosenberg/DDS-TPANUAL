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
import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Dias;
import tpanual.main.Direccion;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;

public class AdministradorDePoiTest {
	
	static HorarioDeAtencion horario;

	@BeforeClass
	public static void setUp(){
		//mapa=Mapa.getInstance() ;
		
		AdministradorDePoi puntoAdminSetUp = new AdministradorDePoi();
		
		horario=new HorarioDeAtencion();
		for (Dias dia:Dias.values()){ //Agrega el horario de atencion lunes a domingo de 9:00 a 14:00
			horario.addRangoDia(1700, 2030, dia);// y de 17:00 a 20:30
			horario.addRangoDia(900, 1400, dia);
		}
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
		.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("CGP");
		palabras2.add("Zona peligrosa");
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		List<Servicio> servicios2=Servicio.getListaServicios("Venta de chicles", "Asesoramiento legal");
		List<Servicio> servicios3=Servicio.getListaServicios("Depositos", "Extracciones");
		
		RubroFW rubro1=RubroFWFactory.getRubro("Muebleria", 700);
		RubroFW rubro2=RubroFWFactory.getRubro("Kiosko", 200);

		servicios.get(0).setHorario(getHorario1());
		servicios.get(1).setHorario(getHorario2());
		servicios.get(2).setHorario(getHorario3());
		servicios2.get(0).setHorario(getHorario3());
		servicios2.get(1).setHorario(getHorario2());
		
		PuntoDeInteres pdi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras2, servicios, 25);
		PuntoDeInteres pdi2=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 2", direccion, palabras2, servicios2, 25);
		PuntoDeInteres pdi3=PuntoDeInteresFactory.getParadaDeColectivo(600, 1200, "Parada de la linea ciento catorce", direccion, palabras2, "114");
		PuntoDeInteres pdi4=PuntoDeInteresFactory.getLocalComercial(-50D, 3000D, "Muebleria los dos hermanos", direccion, palabras2, rubro1, horario);
		PuntoDeInteres pdi5=PuntoDeInteresFactory.getLocalComercial(-5D, 3001D, "Muebleria somos la contra de los dos hermanos", direccion, palabras, rubro1, horario);
		PuntoDeInteres pdi6=PuntoDeInteresFactory.getLocalComercial(-654D, 1286D, "Kiosko no se fia ni al cura parroco", direccion, palabras2, rubro2, horario);
		PuntoDeInteres pdi7=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 49", direccion, palabras, servicios3);
		
		puntoAdminSetUp.agregarPoi(pdi);
		puntoAdminSetUp.agregarPoi(pdi2);
		puntoAdminSetUp.agregarPoi(pdi3);
		puntoAdminSetUp.agregarPoi(pdi4);
		puntoAdminSetUp.agregarPoi(pdi5);
		puntoAdminSetUp.agregarPoi(pdi6);
		puntoAdminSetUp.agregarPoi(pdi7);
			
	}
	private static HorarioDeAtencion getHorario1(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (Dias dia : Dias.values()) {
			if (dia != Dias.DOMINGO && dia != Dias.SABADO)
				horario.addRangoDia(800, 1700, dia);
		}
		return horario;
	}

	private static HorarioDeAtencion getHorario2(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (Dias dia : Dias.values()) {
			if (dia != Dias.DOMINGO)
				horario.addRangoDia(1000, 1600, dia);
		}
		return horario;
	}
	
	private static HorarioDeAtencion getHorario3(){
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (Dias dia : Dias.values()) {
			if (dia != Dias.DOMINGO && dia != Dias.SABADO && dia != Dias.LUNES)
				horario.addRangoDia(1200, 2000, dia);
		}
		return horario;
	}

	//public AdministradorDePoisTest() {
		// TODO Auto-generated constructor stub
//	}
	
	/*
	 * ABMC entrega 2
	 */
	static Mapa mapa=Mapa.getInstance() ;
	
	
	@Test
	public void agregarPoiTest(){
		
	AdministradorDePoi administrador = new AdministradorDePoi();
		
	List<PuntoDeInteres> lista=administrador.busquedaDePuntosDeInteres("");
	
	
	Iterator<PuntoDeInteres> i = lista.iterator();
	boolean aparicion1=false;
	while(i.hasNext()){	
		PuntoDeInteres n=i.next();
		if (n.getNombre().equals("Sucursal 42")) aparicion1=true;
	}
	
	assertFalse(aparicion1);
	
	lista.clear();
	
	Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
			.provincia("Ciudad de Buenos Aires").crearDireccion();
			List<String> palabras=new ArrayList<String>();
			palabras.add("Servicio de cafeteria");
			List<Servicio> servicios2=Servicio.getListaServicios("Venta de chicles", "Asesoramiento legal");
			PuntoDeInteres pdiAAgregar=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 42", direccion, palabras, servicios2);
	
	administrador.agregarPoi(pdiAAgregar);
	
	lista=administrador.busquedaDePuntosDeInteres("");
	
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
		
	AdministradorDePoi administrador = new AdministradorDePoi();
	
	List<PuntoDeInteres> lista=administrador.busquedaDePuntosDeInteres("");
	
	
	Iterator<PuntoDeInteres> i = lista.iterator();
	boolean aparicion1=false;
	boolean aparicion2=false;
	while(i.hasNext()){	
		PuntoDeInteres n=i.next();
		if (n.getNombre().equals("Muebleria los dos hermanos")&&n.buscarCoincidencia("Zona peligrosa")) aparicion1=true;
		if (n.getNombre().equals("Muebleria los dos hermanos")&&n.buscarCoincidencia("Zona segura")) aparicion2=true;
	}

	assertTrue(aparicion1);
	assertFalse(aparicion2);
	
	
	int idAGuardar = 0;
	PuntoDeInteres poiAMod;
	
	Iterator<PuntoDeInteres> j = lista.iterator();
	while(j.hasNext()){	
		poiAMod=j.next();
		if (poiAMod.getNombre().equals("Muebleria los dos hermanos")&&poiAMod.buscarCoincidencia("Zona peligrosa")) idAGuardar=poiAMod.getId();
	}
	
	/*
	 * creo un nuevo poi con los cambios a realizar y le cambio el id para que sea igual al anterior 
	 */
	
	Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
			.provincia("Ciudad de Buenos Aires").crearDireccion();
			List<String> palabrasNuevas=new ArrayList<String>();
			palabrasNuevas.add("Zona segura");
			RubroFW rubro1=RubroFWFactory.getRubro("Muebleria", 700);
	PuntoDeInteres pdiNuevo=PuntoDeInteresFactory.getLocalComercial(-600D, 1023589D, "Muebleria los dos hermanos", direccion, palabrasNuevas, rubro1, horario);
	pdiNuevo.setId(idAGuardar);
	
	administrador.modificarPoi(pdiNuevo);
	
	lista=administrador.busquedaDePuntosDeInteres("");
	
	Iterator<PuntoDeInteres> p = lista.iterator();
	aparicion1=false;
	aparicion2=false;
	while(p.hasNext()){	
		PuntoDeInteres n=p.next();
		if (n.getNombre().equals("Muebleria los dos hermanos")&&n.buscarCoincidencia("Zona segura")) aparicion2=true;
	}
	assertTrue(aparicion2);
		
	
	}
	@Test
	public void eliminarPoiTest(){
		AdministradorDePoi administrador = new AdministradorDePoi();
		
		List<PuntoDeInteres> lista= administrador.busquedaDePuntosDeInteres("");

		
		Iterator<PuntoDeInteres> i = lista.iterator();
		boolean aparicion1=false;
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("GCP Comuna 1")) aparicion1=true;
		}
		
		assertTrue(aparicion1);
		
		lista.clear();
		
		lista=administrador.busquedaDePuntosDeInteres("GCP Comuna 1");
		
		i = lista.iterator();
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			administrador.eliminarPoi(n); 
		}
		
		lista.clear();
		lista=administrador.busquedaDePuntosDeInteres("GCP Comuna 1");
		
		assertTrue(lista.size()==0);
		
	}
	

}
