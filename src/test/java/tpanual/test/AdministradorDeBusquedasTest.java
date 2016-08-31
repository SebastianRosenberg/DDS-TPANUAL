package tpanual.test;
//import static org.junit.*;
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
import tpanual.main.poi.PuntoDeInteres;

public class AdministradorDeBusquedasTest {

	@Test
	public void administradorDeBusquedasDeBancoTest() {

		
		AdministradorDePoi administradorDePoi = new AdministradorDePoi();
				
		//Creo la dirección
		Direccion direccionDeLaSucursal= new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Av. Triunvirato").numero("5201").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Nunca tiene plata");
		List<Servicio> servicios=Servicio.getListaServicios("Depositos");
			
		PuntoDeInteres punto = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Frances", direccionDeLaSucursal, palabrasClave, servicios);
						
		administradorDePoi.agregarPoi(punto);
		
		/**
		 * Agrego manualmente al buffer de busquedas. Asi el sistema cree que esta busqueda se realizo y no requiere ir a servicios externos
		 */
		String[] l={"Banco Frances", "Depositos"};
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		lista.add(punto);

		SesionBusqueda sb=new SesionBusqueda();
		sb.setStringsBuscados(l);
		sb.setPois(lista);
		sb.finalizarBusqueda();
		
		
		List<PuntoDeInteres> listaResultado = administradorDePoi.buscarBancos("Banco Frances", "Depositos");
						
		/**
		 * Compruebo que no haya buscado en servicios externos
		 */
		assertTrue(administradorDePoi.usoBufferBusqueda());
		
		assertTrue( listaResultado.size() > 0);
		assertFalse( listaResultado.size() == 0);
		
	}
	
	
	@Test
	public void administradorDeBusquedasDeCgpTest() {

		AdministradorDePoi admin = new AdministradorDePoi();
				
		//Creo la dirección
		Direccion direccionCGP = new Direccion.DireccionBuilder().barrio("Villa Urquiza").callePrincipal("Miller").numero("2751").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("CGP");
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil");
			
		PuntoDeInteres punto = PuntoDeInteresFactory.getCGP(-34.568574D, -58.482842D, "CGP 12", direccionCGP, palabrasClave, servicios, 12);
					
		admin.agregarPoi(punto);
				
		/**
		 * Agrego manualmente al buffer de busquedas. Asi el sistema cree que esta busqueda se realizo y no requiere ir a servicios externos
		 */
		String[] l={"Registro Civil"};
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		lista.add(punto);
		
		SesionBusqueda sb=new SesionBusqueda();
		sb.setStringsBuscados(l);
		sb.setPois(lista);
		sb.finalizarBusqueda();
			
		
		List<PuntoDeInteres> listaResultado = admin.busquedaDePuntosDeInteres("Registro Civil");
						
		/**
		 * Compruebo que no haya buscado en servicios externos
		 */
		assertTrue(admin.usoBufferBusqueda());
		
		assertTrue( listaResultado.size() > 0);
		assertFalse( listaResultado.size() == 0);	
	}

}
