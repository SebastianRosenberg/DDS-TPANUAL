package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class RealizarBusquedasTest {
	
	@Test
	public void realizarBusquedaComun(){
		
			//seteo Usuario
			Usuario nuevoUsuario = GestorDeUsuarios.getInstance().crearAdministrador("federico", "mailPrueba@hotmail.com","Fede123");
			
			
			//seteo y agrego Poi 
			Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
			List<String> palabras=new ArrayList<String>();
			palabras.add("Servicio de cafeteria");
			palabras.add("Mala Atencion");
			List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");	
			PuntoDeInteres poiNuevo=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 666", direccion, palabras, servicios, 25);
			nuevoUsuario.agregarPoi(poiNuevo);
			

			
			Usuario usuarioAProbar = GestorDeUsuarios.getInstance().crearTerminalActivo("Pedro");
			
			PoiInfoBasica infoPoiBuscado = new PoiInfoBasica();
			infoPoiBuscado.setDireccion(poiNuevo.getDireccion());
			infoPoiBuscado.setId(poiNuevo.getId());
			infoPoiBuscado.setNombre(poiNuevo.getNombre());
			
		
			List<PoiInfoBasica> listaInfoBasica = usuarioAProbar.busquedaBasica("GCP Comuna 666");

			
			assertEquals(listaInfoBasica.get(0).getNombre(),infoPoiBuscado.getNombre());
			assertEquals(listaInfoBasica.get(0).getId(),infoPoiBuscado.getId());
			assertTrue(listaInfoBasica.get(0).getDireccion().equals(infoPoiBuscado.getDireccion()));
			


	}
	
	@Test
	public void realizarBusquedaAvanzada(){
		

		//seteo Usuario
		Usuario administrador = GestorDeUsuarios.getInstance().crearAdministrador("federico", "mailPrueba@hotmail.com","Fede123");
		
		
		//seteo y agrego Poi 
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("CGP");
		palabras2.add("Zona peligrosa");
		
		List<Servicio> servicios3=Servicio.getListaServicios("Depositos", "Extracciones");
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP 42", direccion, palabras2, servicios3, 25);
		administrador.agregarPoi(poi);
		
		Usuario usuarioAProbar = GestorDeUsuarios.getInstance().crearTerminalActivo("Carlos");
		GestorDeUsuarios.getInstance().setearPrivilegios(usuarioAProbar);
		
		PoiInfoBasica infoPoiBuscado = new PoiInfoBasica();
		infoPoiBuscado.setDireccion(poi.getDireccion());
		infoPoiBuscado.setId(poi.getId());
		infoPoiBuscado.setNombre(poi.getNombre());
		
		
		List<PuntoDeInteres> listaCompleta = usuarioAProbar.busquedaAvanzada("Depositos");

		
		assertEquals(listaCompleta.get(0).getNombre(),poi.getNombre());
		assertEquals(listaCompleta.get(0).getId(),poi.getId());
		assertEquals(listaCompleta.get(0).getDireccion(),poi.getDireccion());
		

}
	
}
