package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import administrador.Mapa;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.ComunicacionServiciosExternos;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;

public class PuntoDeInteresTest {

	@Test
	public void puntoCercanoACoordenadaTest(){
		//Creo la direcci�n
		Direccion direccionDeLaSucursal= new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Nunca tiene plata");
		List<Servicio> servicios=Servicio.getListaServicios("Depositos", "Extracciones");
		
		PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Franc�s", direccionDeLaSucursal, palabrasClave, servicios);
								
		assertFalse(puntoFactory.cercanoA(-34.568459D, -58.486106D, 12));
		assertTrue(puntoFactory.cercanoA(-34.570581D, -58.489611D, 12));
	}
	
	@Test
	public void XXXXXXXXXXXXXXXXXXX(){
		ComunicacionServiciosExternos com = new ComunicacionServiciosExternos();
		com.obtenerBancosPorNombreYServicio("Santander", "Pagos");
	}

}
