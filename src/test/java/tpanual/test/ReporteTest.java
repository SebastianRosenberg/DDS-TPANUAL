package tpanual.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.joda.time.Duration;
import org.joda.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import administrador.AdministradorDePoi;
import administrador.SesionBusqueda;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Reporte.CantidadPorBusquedaPorUsuario;
import tpanual.main.Reporte.CantidadPorUsuario;
import tpanual.main.Servicio;
import tpanual.main.Reporte;
import tpanual.main.Reporte.CantidadPorFecha;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class ReporteTest {
	
	static Usuario usr;
	static GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	@BeforeClass
	public static void setUp(){
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
		usr = gestor.crearTerminalActivo("pedritoTester");
		
		
		//Creo la dirección
		Direccion direccionDeLaSucursal= new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Nunca tiene plata");
		List<Servicio> servicios=Servicio.getListaServicios("Depositos");
		PuntoDeInteres punto = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Frances", direccionDeLaSucursal, palabrasClave, servicios);
						
		administradorDePoi.agregarPoi(punto);
		

		usr.busquedaDePuntosDeInteres("Nunca tiene plata");
		//administradorDePoi.buscarBancos("Banco Frances", "Depositos");
		//administradorDePoi.buscarBancos("Banco Frances", "Depositos");
		
	}
	
	@Test
	public void reporteCantidadDeBusquedasPorFechaTest() {
		
		Reporte reporte = new Reporte();
		List<CantidadPorFecha> result = reporte.GenerarReporteCantidadPorFecha();
		assertTrue (result.get(0).cantidad == 1);
		
	}
	
	@Test
	public void reporteCantidadDeResultadosPorUsuarioTest() {
		
		Reporte reporte = new Reporte();
		List<CantidadPorUsuario> result = reporte.GenerarReporteCantidadPorUsuario();
		assertTrue (result.get(0).cantidad == 1);
		
	}
	
	
	@Test
	public void reporteCantidadDeResultadosPorBusquedaPorUsuarioTest() {
		
		Reporte reporte = new Reporte();
		List<CantidadPorBusquedaPorUsuario> result = reporte.GenerarReporteCantidadPorBusquedaPorUsuario(usr);
		assertTrue (result.get(0).cantidad == 1);
		
	}
	
}
