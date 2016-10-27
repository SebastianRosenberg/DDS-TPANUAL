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
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.reportes.AdministradorDeReportes;
import tpanual.reportes.CantidadPorBusquedaPorUsuario;
import tpanual.reportes.CantidadPorFecha;
import tpanual.reportes.CantidadPorUsuario;
import tpanual.reportes.Reporte;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class ReporteTest {
	
	static Usuario usr;
	static GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	@BeforeClass
	public static void setUp(){
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
		usr = gestor.crearTerminalActivo("pedritoTester");
		
		
		//Creo la direcci�n
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
		
		Reporte<CantidadPorFecha> result = AdministradorDeReportes.GenerarReporteCantidadPorFecha();
		assertTrue (result.getLista().get(0).cantidad > 0);
		result.mostrar();
	}
	
	@Test
	public void reporteCantidadDeResultadosPorUsuarioTest() {
		
		Reporte<CantidadPorUsuario> result = AdministradorDeReportes.GenerarReporteCantidadPorUsuario();
		assertTrue (result.getLista().get(0).cantidad > 0);
		result.mostrar();
	}
	
	
	@Test
	public void reporteCantidadDeResultadosPorBusquedaPorUsuarioTest() {
		
		Reporte<CantidadPorBusquedaPorUsuario> result=AdministradorDeReportes.GenerarReporteCantidadPorBusquedaPorUsuario(usr);
		assertTrue (result.getLista().get(0).cantidad > 0);
		result.mostrar();
		
	}
	
}
