package tpanual.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Dias;
import tpanual.main.Direccion;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Mapa;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;

public class MapaTest {

	private static Mapa mapa;
	
	@Test
	public void buscarParadaDeColectivoTest(){
		
		/**
		 * Esta linea de colectivo esta en la lista de puntos de interes
		 */
		String criterio="114";
		
		List<PuntoDeInteres> lista=mapa.buscarPuntosDeInteres(criterio);
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		boolean aparicion=false;		
		while(i.hasNext()){
			
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("Parada de la linea ciento catorce")) aparicion=true;
			
		}
		assertTrue(aparicion);
		
		/**
		 * Esta linea NO esta en la lista
		 */		
		
		criterio="113";
		List<PuntoDeInteres> lista2=mapa.buscarPuntosDeInteres(criterio);		
		
		assertTrue(lista2.isEmpty());		
	}
	
	@Test

	public void buscarLocalesPorRubroTest(){
		
		/**
		 * Busco en minuscula, deberia encontrar aunque este en may�scula en el punto de interes
		 */
		String criterio="muebleria";
		
		List<PuntoDeInteres> lista=mapa.buscarPuntosDeInteres(criterio);
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		boolean aparicion1=false;
		boolean aparicion2=false;
		while(i.hasNext()){
			
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("Muebleria los dos hermanos")) aparicion1=true;
			if (n.getNombre().equals("Muebleria somos la contra de los dos hermanos")) aparicion2=true;
			if (n.getNombre().equals("Kiosko no se fia ni al cura parroco")) assertFalse(true);
			
		}
		assertTrue(aparicion1 && aparicion2);
		
		/**
		 * Esta linea NO esta en la lista
		 */		
		
		criterio="panaderia";
		List<PuntoDeInteres> lista2=mapa.buscarPuntosDeInteres(criterio);		
		
		assertTrue(lista2.isEmpty());		
	}	

	@Test
	public void buscarPalabrasClaveTest(){
		
		String criterio="mala atencion";
		
		List<PuntoDeInteres> lista=mapa.buscarPuntosDeInteres(criterio);
		
		assertTrue(lista.size()==2);
		
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		boolean aparicion1=false;
		boolean aparicion2=false;
		while(i.hasNext()){
			
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("Muebleria somos la contra de los dos hermanos")) aparicion1=true;
			if (n.getNombre().equals("Sucursal 49")) aparicion2=true;
			
		}
		assertTrue(aparicion1 && aparicion2);
		
		criterio="una palabra clave que no esta";
		List<PuntoDeInteres> lista2=mapa.buscarPuntosDeInteres(criterio);		
		
		assertTrue(lista2.isEmpty());		
	}	
	@Test
	public void buscarServicios(){
		
		String criterio="Denuncias";
		
		List<PuntoDeInteres> lista=mapa.buscarPuntosDeInteres(criterio);
		
		assertTrue(lista.size()==1);
		
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		boolean aparicion1=false;
		while(i.hasNext()){
			
			PuntoDeInteres n=i.next();
			if (n.getNombre().equals("GCP Comuna 1")) aparicion1=true;
			
		}
		assertTrue(aparicion1);
		
		criterio="Un servicio que no existe";
		List<PuntoDeInteres> lista2=mapa.buscarPuntosDeInteres(criterio);		
		
		assertTrue(lista2.isEmpty());		
	}	
		
	
	@Test
	public void cercaniaCgpTest(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionCGP = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Miller").numero("2751").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("CGP");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil");
		
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getCGP(-34.568574D, -58.482842D, "CGP 12", direccionCGP, palabrasClave, servicios, 12);
				
		assertTrue(mapa.esCercano(puntoFactory, -34.571896D, -58.490224D, 12));
		
		assertFalse(mapa.esCercano(puntoFactory, -34.578546D, -58.469453D, 15));
	}

	@Test
	public void cercaniaAUnaParadaTest(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionDeLaParada = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("DR. Ignacio Rivera").numero("1889").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Cerca a una Plaza");
		
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getParadaDeColectivo(-34.572426D, -58.489022D,"Parada L��ea 176", direccionDeLaParada, palabrasClave, "176");
				
		assertTrue(mapa.esCercano(puntoFactory, -34.572713D, -58.488448D, 12));
		assertFalse(mapa.esCercano(puntoFactory, -34.578546D, -58.469453D, 15));
	}

	@Test
	public void cercaniaALibreriaEscolarTest(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionDeLaLibreria = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5389").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Surtida");
		palabrasClave.add("Excelente Atenci�n");
		RubroFW rubro = RubroFWFactory.getRubro("Libreria Escolar", 500);
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getLocalComercial(-34.569553D, -58.492019D, "Lo de Tony", direccionDeLaLibreria, palabrasClave, rubro);
		
		assertTrue(mapa.esCercano(puntoFactory, -34.572713D, -58.488448D, 12));
		assertFalse(mapa.esCercano(puntoFactory, -34.578546D, -58.469453D, 15));
	}
	
	@Test
	public void cercaniaKioskoTest(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionDelKiosko = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5389").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Venden alcohol");
		palabrasClave.add("Venden Panchos");
		RubroFW rubro=RubroFWFactory.getRubro("Kiosko",  200);
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getLocalComercial(-34.573119D, -58.489301D, "Lo + Pancho", direccionDelKiosko, palabrasClave, rubro);
		
		assertTrue(mapa.esCercano(puntoFactory, -34.573001D, -58.490937D, 12));
		assertFalse(mapa.esCercano(puntoFactory, -34.578546D, -58.469453D, 15));
	}
	
	
	//para libreria 
	//assertFalse(mapa.esCercano(puntoFactory, -34.574647D, -58.485889D, 12));
	
	@Test
	public void disponibilidadParadaColectivoTodoElDiaDomingoTest(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		
		PuntoDeInteres paradaDeColectivo = PuntoDeInteresFactory.getParadaDeColectivo(600, 1200, "Parada de la linea ciento catorce", direccion, palabras, "114");
		
		for (int hora=0000;hora<2400;hora=hora+30)
			assertTrue(mapa.estaDisponible(paradaDeColectivo, Dias.DOMINGO, hora, ""));
		
	}
	
	@Test
	public void disponibilidadSucursalDeBancoDiaLunesALas1300Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		
		PuntoDeInteres sucursalDeBanco=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 49", direccion, palabras);
		
		assertTrue(mapa.estaDisponible(sucursalDeBanco, Dias.LUNES, 1300, ""));
		
	}
	
	@Test
	public void disponibilidadSucursalDeBancoDiaViernesALas2200Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		
		PuntoDeInteres sucursalDeBanco=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 49", direccion, palabras);
		
		assertFalse(mapa.estaDisponible(sucursalDeBanco, Dias.SABADO, 2200, ""));
		
	}
	
	@Test
	public void disponibilidadCarrouselDiaJuevesALas1730Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();

		RubroFW rubroCarrousel=RubroFWFactory.getRubro("Carrousel", 200);
		PuntoDeInteres carrousel = PuntoDeInteresFactory.getLocalComercial(-654D, 1286D, "Calesita", direccion, palabras, rubroCarrousel);
		
		assertTrue(mapa.estaDisponible(carrousel, Dias.JUEVES, 1730, ""));
		
	}
	
	@Test
	public void disponibilidadCarrouselDiaMartesALas1500Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();

		RubroFW rubroCarrousel=RubroFWFactory.getRubro("Carrousel", 200);
		PuntoDeInteres carrousel = PuntoDeInteresFactory.getLocalComercial(-654D, 1286D, "Calesita", direccion, palabras, rubroCarrousel);
		
		assertFalse(mapa.estaDisponible(carrousel, Dias.MARTES, 1500, ""));
		
	}
	
	@Test
	public void disponibilidadServicioDenunciasDiaMiercolesALas1100Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();

		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		servicios.get(0).setHorario(getHorario1());
		servicios.get(1).setHorario(getHorario2());
		servicios.get(2).setHorario(getHorario3());		
		
		PuntoDeInteres cgp=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		assertTrue(mapa.estaDisponible(cgp, Dias.MARTES, 1100, "denuncias"));
		
	}
	
	@Test
	public void disponibilidadDeAlgunServicioDiaLunesALas0900Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();

		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		servicios.get(0).setHorario(getHorario1());
		servicios.get(1).setHorario(getHorario2());
		servicios.get(2).setHorario(getHorario3());		
		
		PuntoDeInteres cgp=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		assertTrue(mapa.estaDisponible(cgp, Dias.LUNES, 900, ""));
		
	}
	
	@Test
	public void disponibilidadDeNingunServicioDiaDomingoALas0900Test(){
		Mapa mapa = Mapa.getInstance();
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();

		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		servicios.get(0).setHorario(getHorario1());
		servicios.get(1).setHorario(getHorario2());
		servicios.get(2).setHorario(getHorario3());		
		
		PuntoDeInteres cgp=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		assertFalse(mapa.estaDisponible(cgp, Dias.DOMINGO, 900, ""));
		
	}
	
	/**
	 * Este metodo deberia ser usado por los demas test para que este centralizado la creacion del Mapa con puntos adentro
	 * @return
	 */
	
	@BeforeClass
	public static void setUp(){
		mapa=Mapa.getInstance() ;
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
		.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		List<String> palabras2=new ArrayList<String>();
		palabras.add("CGP");
		palabras.add("Zona peligrosa");
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		List<Servicio> servicios2=Servicio.getListaServicios("Venta de chicles", "Asesoramiento legal");
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
		PuntoDeInteres pdi4=PuntoDeInteresFactory.getLocalComercial(-50D, 3000D, "Muebleria los dos hermanos", direccion, palabras2, rubro1);
		PuntoDeInteres pdi5=PuntoDeInteresFactory.getLocalComercial(-5D, 3001D, "Muebleria somos la contra de los dos hermanos", direccion, palabras, rubro1);
		PuntoDeInteres pdi6=PuntoDeInteresFactory.getLocalComercial(-654D, 1286D, "Kiosko no se fia ni al cura parroco", direccion, palabras2, rubro2);
		PuntoDeInteres pdi7=PuntoDeInteresFactory.getSucursal(-600D, 1023589D, "Sucursal 49", direccion, palabras);
		
		
		mapa.agregarPunto(pdi);
		mapa.agregarPunto(pdi2);
		mapa.agregarPunto(pdi3);
		mapa.agregarPunto(pdi4);
		mapa.agregarPunto(pdi5);
		mapa.agregarPunto(pdi6);
		mapa.agregarPunto(pdi7);
		
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
}

