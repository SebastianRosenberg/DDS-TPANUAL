package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
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
			Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
					.provincia("Ciudad de Buenos Aires").crearDireccion();
			List<String> palabras=new ArrayList<String>();
			palabras.add("Servicio de cafeteria");
			palabras.add("Mala Atencion");
			List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");	
			PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras, servicios, 25);
			nuevoUsuario.agregarPoi(poi);
			
			Usuario usuarioAProbar = GestorDeUsuarios.getInstance().crearTerminalActivo("Pedro");
			
			PoiInfoBasica infoPoiBuscado = new PoiInfoBasica();
			infoPoiBuscado.setDireccion(poi.getDireccion());
			infoPoiBuscado.setId(poi.getId());
			infoPoiBuscado.setNombre(poi.getNombre());
			
			
			List<PoiInfoBasica> listaInfoBasica = usuarioAProbar.realizarBusqueda("GCP Comuna 1");
			
			
			assertEquals(listaInfoBasica.get(0).getNombre(),poi.getNombre());
			assertEquals(listaInfoBasica.get(0).getId(),poi.getId());
			assertEquals(listaInfoBasica.get(0).getDireccion(),poi.getDireccion());
			
			

	}
	
	@Test
	public void realizarBusquedaAvanzada(){
		

		//seteo Usuario
		Usuario administrador = GestorDeUsuarios.getInstance().crearAdministrador("federico", "mailPrueba@hotmail.com","Fede123");
		
		
		//seteo y agrego Poi 
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("CGP");
		palabras2.add("Zona peligrosa");
		
		List<Servicio> servicios3=Servicio.getListaServicios("Depositos", "Extracciones");
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP 42", direccion, palabras2, servicios3, 25);
		administrador.agregarPoi(poi);
		
		Usuario usuarioAProbar = GestorDeUsuarios.getInstance().crearTerminalActivo("Carlos");
		GestorDeUsuarios.getInstance().darPrivilegioBusquedaAvanzada(usuarioAProbar);
		
		PoiInfoBasica infoPoiBuscado = new PoiInfoBasica();
		infoPoiBuscado.setDireccion(poi.getDireccion());
		infoPoiBuscado.setId(poi.getId());
		infoPoiBuscado.setNombre(poi.getNombre());
		
		
		List<PoiInfoBasica> listaInfoBasica = usuarioAProbar.realizarBusquedaAvanzada(usuarioAProbar, "GCP 42", null, null, "Depositos");
		System.out.println(listaInfoBasica.size());
		
		assertEquals(listaInfoBasica.get(0).getNombre(),poi.getNombre());
		assertEquals(listaInfoBasica.get(0).getId(),poi.getId());
		assertEquals(listaInfoBasica.get(0).getDireccion(),poi.getDireccion());
		

}
	
}
