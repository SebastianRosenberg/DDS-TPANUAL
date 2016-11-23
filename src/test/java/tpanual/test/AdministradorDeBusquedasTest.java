package tpanual.test;
//import static org.junit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDeBusquedas;
import administrador.AdministradorDePoi;
import administrador.SesionBusqueda;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.jsfcontrollers.BusquedaDePoisController;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.usuario.estado.Activo;
import tpanual.utilitarios.Utilitarios;

public class AdministradorDeBusquedasTest {
	
	private String usuario;
	
	@BeforeClass
	public void initialize(){
		usuario = "Terminal 1";
		GestorDeUsuarios.getInstance().crearTerminalActivo(usuario);
	}

	@Test
	public void administradorDeBusquedasDeBancoTest() {

		BusquedaDePoisController busquedaDePoisController = new BusquedaDePoisController();
		List<String> palabras = new ArrayList<String>();
		palabras.add("galicia");
		List<PoiPojo> resultados = busquedaDePoisController.buscarPois(palabras, usuario);
						
		/**
		 * Compruebo que no haya buscado en servicios externos
		 */
		assertTrue(resultados.size() == 1);
		
	}
	
	
	@Test
	public void administradorDeBusquedasDeCgpTest() {

		BusquedaDePoisController busquedaDePoisController = new BusquedaDePoisController();
		List<String> palabras = new ArrayList<String>();
		palabras.add("mataderos");
		palabras.add("almagro");
		List<PoiPojo> resultados = busquedaDePoisController.buscarPois(palabras, usuario);
						
		/**
		 * Compruebo que no haya buscado en servicios externos
		 */
		assertTrue(resultados.size() == 2);
	}

}
