package tpanual.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Mapa;
import tpanual.main.PuntoDeInteres;
import tpanual.main.Servicio;



public class MapaTest {

	
	@Test
	public void buscarParadaDeColectivoTest(){
		
		/**
		 * Esta linea de colectivo esta en la lista de puntos de interes
		 */
		String criterio="114";
		
		List<PuntoDeInteres> lista=getMapaConPuntosDeInteres().obtenerPuntosDeInteres(criterio);
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
		List<PuntoDeInteres> lista2=getMapaConPuntosDeInteres().obtenerPuntosDeInteres(criterio);		
		
		assertTrue(lista2.isEmpty());		
	}
	
	@Test
	public void testEsCercanoACgp(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionCGP = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Miller").numero("2751").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("CGP");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil");
		
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getCGP(-34.568574D, -58.482842D, "CGP 12", direccionCGP, palabrasClave, servicios, 12);
				
		assertTrue(mapa.esCercano(puntoFactory, -34.571896D, -58.490224D, 12));
		
	}

	@Test
	public void testEsCercanoAUnaParada(){
		Mapa mapa = Mapa.getInstance();
		//Creo la direcci�n
		Direccion direccionDeLaParada = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("DR. Ignacio Rivera").numero("1889").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Cerca a una Plaza");
		
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getParadaDeColectivo(-34.572426D, -58.489022D,"Parada L�nea 176", direccionDeLaParada, palabrasClave, "176");
				
		assertTrue(mapa.esCercano(puntoFactory, -34.572713D, -58.488448D, 12));
		
	}

	/**
	 * Este metodo deberia ser usado por los demas test para que este centralizado la creacion del Mapa con puntos adentro
	 * @return
	 */
	
	private Mapa getMapaConPuntosDeInteres(){
		Mapa mapa=Mapa.getInstance();
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
		.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras=new ArrayList<String>();
		palabras.add("CGP");
		palabras.add("Zona peligrosa");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");

		PuntoDeInteres pdi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		PuntoDeInteres pdi2=PuntoDeInteresFactory.getParadaDeColectivo(600, 1200, "Parada de la linea ciento catorce", direccion, palabras, "114");
		
		
		mapa.agregarPunto(pdi);
		mapa.agregarPunto(pdi2);
		
		return mapa;
	}

}
