package tpanual.test;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.UsuariosFactory;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;
import tpanual.temporizador.Temporizador;

public class EmailTest {
	
	@Test
	public void envioCorrectoTest() {
		
		//seteo Usuario
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador("federico", "mailPrueba@hotmail.com");
		
		//seteo Poi A buscar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		nuevoUsuario.agregarPoi(poi);
		
		
		
		Instant inicio = Temporizador.TiempoInicioBusqueda ();
		
		nuevoUsuario.busquedaDePuntosDeInteres("");
		
		Duration duration = Temporizador.LapsoBusqueda(inicio);

		Temporizador.ChequeoLapso (duration, nuevoUsuario);
		
		assertTrue(Constantes.TIEMPO_MAXIMO_CONSULTA.compareTo(duration)==1);
		
	}
	
	
	@Test
	public void envioIncorrectoTest() {
		
		//seteo Usuario
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador("federico", "mailPrueba@hotmail.com");
		
		//seteo Poi A buscar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		nuevoUsuario.agregarPoi(poi);
		
		
		
		Instant inicio = Temporizador.TiempoInicioBusqueda ();
		
		nuevoUsuario.busquedaDePuntosDeInteres("");
		
		try {
		    Thread.sleep(15000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		Duration duration = Temporizador.LapsoBusqueda(inicio);

		Temporizador.ChequeoLapso (duration, nuevoUsuario);
		
		assertFalse(Constantes.TIEMPO_MAXIMO_CONSULTA.compareTo(duration)==1);
		
	}
}
