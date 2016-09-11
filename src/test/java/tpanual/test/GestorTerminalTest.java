package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import administrador.AdministradorDePoi;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class GestorTerminalTest {

	@Test
	public void  registroTerminal() {
	
	
	Usuario nuevoUsuarioTerminal = GestorDeUsuarios.getInstance().crearTerminalActivo("terminalAbasto");
	assertTrue(nuevoUsuarioTerminal!=null);
	boolean result = (GestorDeUsuarios.getInstance().getTerminales()).contains(nuevoUsuarioTerminal);
	assertTrue(result);
	
	}
	
	@Test
	public void  busquedaPoi() {
	
		//creo un poi a buscar
		AdministradorDePoi admin = new AdministradorDePoi();
		
		Direccion direccionCGP = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Miller").numero("2751").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("CGP");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil");
			
		PuntoDeInteres punto = PuntoDeInteresFactory.getCGP(-34.568574D, -58.482842D, "CGP 12", direccionCGP, palabrasClave, servicios, 12);
					
		admin.agregarPoi(punto);
		
		//creo mi usuario que va a buscar
		Usuario nuevoUsuarioTerminal = GestorDeUsuarios.getInstance().crearTerminalActivo("terminalVillage");
			
		List<PuntoDeInteres> lista = nuevoUsuarioTerminal.busquedaDePuntosDeInteres("");
		
		assertTrue (lista.contains(punto));
	
	}
}
