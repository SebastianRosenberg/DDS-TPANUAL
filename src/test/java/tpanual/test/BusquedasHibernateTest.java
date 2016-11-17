package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDePoi;
import administrador.Busqueda;
import administrador.SesionBusqueda;
//import administrador.AdministradorDeBusquedas;
//import administrador.AdministradorDePoi;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

/*
 * Aclaraci�n: est� test queda deprecado porque las b�usquedas pasan a 
 * persistirse en Mongo en vez de Hibernate
 * 
 */

public class BusquedasHibernateTest {
	static HibernateFactorySessions hs;
	static Integer idUsuarioEliminado;

	@Test
	public void persistirBusquedaTest() {
		hs = new HibernateFactorySessions();
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		Usuario nuevoUsuarioAdmin = gestor.crearAdministrador("CARC", "CARC@hotmail.com", "1889");
		Busqueda busqueda;
		Busqueda busquedaBd;
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
		Integer busquedaId = null;

		// Creo la direcci�n
		Direccion direccionDeLaSucursal = new Direccion.DireccionBuilder().barrio("Villa Urquiza")
				.callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Nunca tiene plata");
		List<Servicio> servicios = Servicio.getListaServicios("Depositos");

		PuntoDeInteres punto = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Frances",
				direccionDeLaSucursal, palabrasClave, servicios);

		administradorDePoi.agregarPoi(punto);
		/**
		 * Agrego manualmente al buffer de busquedas. Asi el sistema cree que
		 * esta busqueda se realizo y no requiere ir a servicios externos
		 */
		String[] l = { "Banco Frances", "Depositos" };
		List<PuntoDeInteres> lista = new ArrayList<PuntoDeInteres>();
		lista.add(punto);
		// para que funcione sin error vargar usuario ac� y hacer otro m�todo
		// que guarde la b�squeda con un usuario que exista en la base
		SesionBusqueda sb = new SesionBusqueda();
		sb.setUsuario(nuevoUsuarioAdmin);
		sb.setStringsBuscados(l);
		sb.setPois(lista);
		busqueda = sb.obtenerBusqueda();
		busquedaId = hs.add(busqueda);

		busquedaBd = hs.obtenerBusqueda(busquedaId);

		//assertTrue(busquedaBd.getId() == busquedaId);
	}

}
