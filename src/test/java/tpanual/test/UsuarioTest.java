package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import administrador.Mapa;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.jsfcontrollers.BusquedaDePoisController;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.UsuariosFactory;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class UsuarioTest {

	static GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	static PuntoDeInteres poi;

	@BeforeClass
	public static void initialize() {
		/* Set up Poi */
		Direccion direccion = new Direccion.DireccionBuilder().callePrincipal("Rivadavia").numero("7777").barrio("Flores")
				.crearDireccion();
		List<String> palabras = new ArrayList<String>();
		palabras.add("Usuario Test");

		List<Servicio> servicios = Servicio.getListaServicios("ServicioUsuario1", "ServicioUsuario2");

		poi = PuntoDeInteresFactory.getCGP(2500D, 3200D, "CGP Usuario Test", direccion, palabras, servicios, 25);

		/* Seteo usuarios */
		gestor.crearAdministrador("AdministradorTest", "esuntest@gmail.com", "1234cinco");
		
		gestor.crearTerminalActivo("UsuarioActivoTest");
		
		gestor.crearTerminalNoActivo("UsuarioNoActivoTest");

	}

	@Test
	public void administradorAgregarPoiTest() {

		Usuario admin = gestor.buscarUsuarioPorNombre("AdministradorTest");
		
		/* Tarea del admin */
		admin.agregarPoi(poi);

		/* Busco el poi que acabo de agregar */
		assertTrue(admin.busquedaDePuntosDeInteres("cgp usuario test", true).size()>0);
	}

	@Test
	public void usuarioTerminalActivoBusquedaTest() {

		Usuario usuarioTerminalActivo = gestor.buscarUsuarioPorNombre("UsuarioActivoTest");
		assertTrue(usuarioTerminalActivo.busquedaDePuntosDeInteres("cgp usuario test", true).size()>0);
	}

	@Test
	public void usuarioTerminalNoActivoBusquedaTest() {

		Usuario usuarioTerminalNoActivo = gestor.buscarUsuarioPorNombre("UsuarioNoActivoTest");

		assertTrue(usuarioTerminalNoActivo.busquedaDePuntosDeInteres("cgp usuario test", true) == null);
	}

}
