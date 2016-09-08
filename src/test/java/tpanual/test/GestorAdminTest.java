package tpanual.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.factory.UsuariosFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeAdministradores;
import tpanual.temporizador.Temporizador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class GestorAdminTest {
	
	@Test
	public void envioCorrectoTest() {
		
		//seteo Gestor
		GestorDeAdministradores gestor = new GestorDeAdministradores();
		
		
		//seteo Usuario admin
		Usuario nuevoUsuario = gestor.crearAdministrador("federico", "mailPrueba@hotmail.com",1,  "banana");
		
		//seteo Terminal
		
		Usuario usuarioTerminal =  UsuariosFactory.getUsuarioTerminalActivo("terminal 8", 2);
		
		//seteo datos del Poi A usar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		
		//revisar el casteo
		Usuario admin = usuarioTerminal.Loguear(nuevoUsuario, "banana");
		
		List<PuntoDeInteres> listaResultado = admin.busquedaDePuntosDeInteres("Registro Civil");
		
		assertFalse( listaResultado.size() > 0);
		assertTrue( listaResultado.size() == 0);	
		
		admin.agregarPoi(poi);
		
		List<PuntoDeInteres> listaResultado2 = admin.busquedaDePuntosDeInteres("Registro Civil");
		
		assertTrue( listaResultado2.size() > 0);
		assertFalse( listaResultado2.size() == 0);	
		
		
		
		
		
	}
	

}
