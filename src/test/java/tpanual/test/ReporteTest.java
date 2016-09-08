package tpanual.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import administrador.AdministradorDeBusquedas;
import administrador.AdministradorDePoi;
import administrador.SesionBusqueda;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.Reporte;
import tpanual.main.Reporte.CantidadPorFecha;
import tpanual.main.poi.PuntoDeInteres;

public class ReporteTest {

	@Test
	public void reporteCantidadDeBusquedasPorFechaTest() {
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
		Reporte reporte = new Reporte();
		
		//Creo la dirección
		Direccion direccionDeLaSucursal= new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Nunca tiene plata");
		List<Servicio> servicios=Servicio.getListaServicios("Depositos");
			
		PuntoDeInteres punto = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Frances", direccionDeLaSucursal, palabrasClave, servicios);
						
		administradorDePoi.agregarPoi(punto);
		
		administradorDePoi.buscarBancos("Banco Frances", "Depositos");
		administradorDePoi.buscarBancos("Banco Frances", "Depositos");
		
		
		List<CantidadPorFecha> reportePorFecha = reporte.GenerarReporteCantidadPorFecha();
		
		assertTrue (reportePorFecha.get(0).cantidad == 2);
		
	}
}
