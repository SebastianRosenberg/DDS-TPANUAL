package tpanual.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDePoi;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.UsuariosFactory;
import tpanual.temporizador.Temporizador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class GestorUsuarioTest 
{
	
	
	
	@Test
	public void creacionAdmin() 
	{
		Usuario nuevoUsuario = GestorDeUsuarios.getInstance().crearAdministrador("sebas", "mailsebas@hotmail.com","peras");
		
		assertTrue(GestorDeUsuarios.getInstance().getAdministradores().contains(nuevoUsuario));
	}
	
	@Test
	public void  registroTerminalActivo() 
	{
	
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalActivo("terminalAbasto");
		
		assertTrue(nuevoUsuarioTerminal!=null);
		
		boolean result = (gestor.getTerminales()).contains(nuevoUsuarioTerminal);
		
		assertTrue(result);
	
	}
	
	@Test
	public void  registroTerminalNoActivo() 
	{
	
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalNoActivo("terminalVillage");
		
		assertTrue(nuevoUsuarioTerminal!=null);
		
		boolean result = (gestor.getTerminales()).contains(nuevoUsuarioTerminal);
		
		assertTrue(result);
	
	}
	

	@Test
	public void envioCorrectoTest() 
	{
		

		//seteo Usuario admin
		Usuario nuevoUsuario = GestorDeUsuarios.getInstance().crearAdministrador("federico", "mailPrueba@hotmail.com", "banana");
		
		//seteo Terminal
		
		Usuario usuarioTerminal =  GestorDeUsuarios.getInstance().crearTerminalActivo("terminal 8");
		
		//seteo datos del Poi A usar
		
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		
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
	
	@Test
	public void logueoAdmin() 
	{
		//creo gestor
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		//creo terminal
		Usuario nuevoTerminal = gestor.crearTerminalActivo("terminalCaballito");
		
		assertTrue(nuevoTerminal!=null);
		
		//creo un admin para loguear primero
		Usuario admin = gestor.crearAdministrador("marcelo", "mailmarcelo@hotmail.com","manzanas");
		
		assertTrue(admin!=null);
		
		gestor.logueoComoAdmin(admin, "manzanas", nuevoTerminal);
		
		boolean result = gestor.getAdminsLogueados().containsKey(admin.getId());
		
		assertTrue(result);
		
		//creo el segundo admin para esa misma terminal
		Usuario nuevoAdmin = gestor.crearAdministrador("alejandro", "mailalejandro@hotmail.com","melocoton");
		
		assertFalse(gestor.getAdminsLogueados().containsKey(nuevoAdmin.getId()));
		
		assertTrue(nuevoAdmin!=null);
		
		gestor.logueoComoAdmin(nuevoAdmin, "melocoton", nuevoTerminal);		
		
		result = gestor.getAdminsLogueados().containsKey(nuevoAdmin.getId());
		
		assertTrue(result);
		
		//contiene a ambos admins
		result = gestor.getAdminsLogueados().containsKey(admin.getId());
		
		assertTrue(result);
		
	}
	
	
	@Test
	public void deslogueo () 
	{
				//creo gestor
				GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
				
				//creo terminal
				Usuario nuevoTerminal = gestor.crearTerminalActivo("terminalLaRural");
				
				assertTrue(nuevoTerminal!=null);
				
				//creo un admin para loguear primero
				Usuario admin = gestor.crearAdministrador("walter", "mailwalter@hotmail.com","aguanteLaUBA");
				
				assertTrue(admin!=null);
				
				gestor.logueoComoAdmin(admin, "aguanteLaUBA", nuevoTerminal);
				
				boolean result = gestor.getAdminsLogueados().containsKey(admin.getId());
				
				assertTrue(result);
				
				//deslogueo al administrador
				gestor.deslogueoAdmin(admin);
				
				//reviso que se haga efectivo el deslogueo
				result = gestor.getAdminsLogueados().containsKey(admin.getId());
				
				assertFalse(result);
						
	}
	
	
	@Test
	public void  busquedaPoi() 
	{
	
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		Usuario nuevoUsuario = gestor.crearAdministrador("rodrigo", "mailrodrigo@hotmail.com","duraznos");
	
		//creo un poi a buscar
		
		Direccion direccionCGP = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Miller").numero("2751").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("CGP");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil");
			
		PuntoDeInteres punto = PuntoDeInteresFactory.getCGP(-34.568574D, -58.482842D, "CGP 12", direccionCGP, palabrasClave, servicios, 12);
					
		nuevoUsuario.agregarPoi(punto);
		
		//creo mi usuario que va a buscar
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalActivo("terminalVillage");
			
		List<PuntoDeInteres> lista = nuevoUsuarioTerminal.busquedaDePuntosDeInteres("");
		
		assertTrue (lista.contains(punto));
	
	}
	
	@Test
	public void obtenerTerminalesPorID() 
	{
		//creo gestor
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		//creo terminal
		Usuario terminal = gestor.crearTerminalActivo("terminalObelisco");
		
		assertTrue(terminal!=null);
		
		//obtengo el id de la terminal que cree
		int idTerminal = terminal.getId();
				
		//busco en mi lista de terminales si puedo obtener la terminal que acabo de crear mediante su ID
		Usuario usuario = gestor.getTerminalPorID(idTerminal);
		
		assertTrue(usuario!=null);
		
		assertTrue(gestor.getTerminales().containsKey(usuario.getId()));

		
	}
	
	
	@Test
	public void obtenerAdministradoresPorID() 
	{
		//creo gestor
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		
		//creo administrador
		Usuario administrador = gestor.crearAdministrador("nombreDePersona", "mailDePErsona@yahoo.com", "constrse�aDePersona");
		
		assertTrue(administrador!=null);
		
		int idAdministrador = administrador.getId();
				
		Usuario usuario = gestor.getAdministradoresPorID(idAdministrador);
		
		assertTrue(usuario!=null);
		
		boolean result = gestor.getAdministradores().containsKey(usuario.getId());
		
		assertTrue(result);
		
	}
	
}
