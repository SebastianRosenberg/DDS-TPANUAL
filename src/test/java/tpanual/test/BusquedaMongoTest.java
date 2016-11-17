package tpanual.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import administrador.AdministradorDePoi;
import administrador.Busqueda;
import administrador.SesionBusqueda;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.mongo.MongoDBConnection;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class BusquedaMongoTest {

	@Test
	public void persistirBusquedaTest() {

		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		Usuario nuevoUsuarioAdmin = gestor.crearAdministrador("CARC", "CARC@hotmail.com", "1889");
		Busqueda busqueda;
		Busqueda busquedaBd;
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
		Integer busquedaId = null;

		// Creo la dirección
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
		// para que funcione sin error vargar usuario acá y hacer otro método
		// que guarde la búsqueda con un usuario que exista en la base
		SesionBusqueda sb = new SesionBusqueda();
		sb.setUsuario(nuevoUsuarioAdmin);
		sb.setStringsBuscados(l);
		sb.setPois(lista);
		busqueda = sb.obtenerBusqueda();

		// persistir
		MongoDBConnection.getInstance().getDatastore().save(busqueda);

	}
}
