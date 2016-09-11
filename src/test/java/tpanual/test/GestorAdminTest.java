package tpanual.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.UsuariosFactory;
import tpanual.temporizador.Temporizador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class GestorAdminTest {
	
	
	
	@Test
	public void creacionAdmin() 
	{
		Usuario nuevoUsuario = GestorDeUsuarios.getInstance().crearAdministrador("sebas", "mailsebas@hotmail.com","peras");
		assertTrue(GestorDeUsuarios.getInstance().getAdministradores().contains(nuevoUsuario));
	}
	

	@Test
	public void envioCorrectoTest() {
		

		//seteo Usuario admin
		Usuario nuevoUsuario = GestorDeUsuarios.getInstance().crearAdministrador("federico", "mailPrueba@hotmail.com", "banana");
		
		//seteo Terminal
		
		Usuario usuarioTerminal =  GestorDeUsuarios.getInstance().crearTerminalActivo("terminal 8");
		
		//seteo datos del Poi A usar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		
		List<String> palabras=new ArrayList<String>();
		palabras.add("Servicio de cafeteria");
		palabras.add("Mala Atencion");
		
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
		
		Usuario admin = usuarioTerminal.logueo(nuevoUsuario, "banana", usuarioTerminal);
		
		List<PuntoDeInteres> listaResultado = admin.busquedaDePuntosDeInteres("Registro Civil");
		
		assertFalse( listaResultado.contains(poi));
		
		admin.agregarPoi(poi);
		
		List<PuntoDeInteres> listaResultado2 = admin.busquedaDePuntosDeInteres("Registro Civil");
		
		assertTrue( listaResultado2.contains(poi));				
		
	}
	
	
	
}
