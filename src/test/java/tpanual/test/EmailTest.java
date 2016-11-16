package tpanual.test;

import static org.junit.Assert.*;

import org.joda.time.Duration;
import org.joda.time.Instant;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.UsuariosFactory;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;
import tpanual.temporizador.Temporizador;

public class EmailTest {
	static GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	
	@Test
	public void envioCorrectoTest() {
		
		//seteo Usuario
		Usuario nuevoUsuario = gestor.crearAdministrador("federico", "mailPrueba@hotmail.com","Fede123");
		
		
		//seteo Poi A buscar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		nuevoUsuario.agregarPoi(poi);
		
		
		
		Temporizador temp = new Temporizador();
		temp.tiempoInicioBusqueda();
		
		nuevoUsuario.busquedaDePuntosDeInteres("", true);

		assertTrue(Constantes.TIEMPO_MAXIMO_CONSULTA.compareTo(temp.ChequeoLapso(nuevoUsuario))>=0);
		
	}
	
	
	@Test
	public void envioIncorrectoTest() {
		
		//seteo Usuario
		
		Usuario nuevoUsuario = gestor.crearAdministrador("federico", "mailPrueba@hotmail.com","Fede123");
		//seteo Poi A buscar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		nuevoUsuario.agregarPoi(poi);
		
		
		
		Temporizador temp = new Temporizador();
		temp.tiempoInicioBusqueda();
		
		nuevoUsuario.busquedaDePuntosDeInteres("", true);
		
				
		try {
		    Thread.sleep(15000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}

		assertTrue(Constantes.TIEMPO_MAXIMO_CONSULTA.compareTo(temp.ChequeoLapso(nuevoUsuario))<0);
		
	}
}
