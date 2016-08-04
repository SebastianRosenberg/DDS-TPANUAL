package tpanual.test;
//import static org.junit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import administrador.AdministradorDePoi;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;

public class AdministradorDeBusquedasTest {

	@Test
	public void administradorDeBusquedasDeBancoTest() {
		// TODO Auto-generated constructor stub
		
				//AdministradorDeBusquedas busqueda = AdministradorDeBusquedas.getInstance();
		AdministradorDePoi punto = new AdministradorDePoi();
				
				//Creo la direcci�n
				Direccion direccionDeLaSucursal= new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
				ArrayList<String> palabrasClave = new ArrayList<String>();
				palabrasClave.add("Nunca tiene plata");
				List<Servicio> servicios=Servicio.getListaServicios("Depositos", "Extracciones");
				
				PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Frances", direccionDeLaSucursal, palabrasClave, servicios);
				
				
				punto.agregarPoi(puntoFactory);
				
				List<PuntoDeInteres> listaResultado = punto.busquedaDePuntosDeInteres("Banco Frances");
				
				
				assertTrue( listaResultado.size() > 0);
				assertFalse( listaResultado.size() == 0);
		
	}

}
