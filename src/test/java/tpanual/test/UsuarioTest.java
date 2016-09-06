package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class UsuarioTest {

	
	@Test
	public void administradorAgregarPoiTest(){
		
		/*Set up Poi*/
		Direccion direccion=new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").codigoPostal("1701").pais("Argentina")
				.provincia("Ciudad de Buenos Aires").crearDireccion();
				List<String> palabras=new ArrayList<String>();
				palabras.add("Servicio de cafeteria");
				palabras.add("Mala Atencion");
		
		List<String> palabras2=new ArrayList<String>();
		palabras2.add("CGP");
		palabras2.add("Zona peligrosa");
		
		List<Servicio> servicios=Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		
		PuntoDeInteres poi=PuntoDeInteresFactory.getCGP(2500D, 3200D, "GCP Comuna 1", direccion, palabras2, servicios, 25);
		
		/*Seteo usuario*/
		Usuario usuarioAdmin = new Usuario();
		Administrador administrador = new Administrador("mail@gmail.com", 1, "Seba");
		usuarioAdmin.setTipoDeUsuario(administrador);
		
		/*Tareas del usuario*/
		usuarioAdmin.agregarPoi(poi);
		
		/*asigno a una lista el resultado de la búsqueda*/
		List<PuntoDeInteres> lista= usuarioAdmin.busquedaDePuntosDeInteres("");
		
		assertTrue(lista.size()> 0);
		
	}
	
	@Test
	public void administradorEliminarPoiTest(){
		/*Seteo usuario*/
		Usuario usuarioAdmin = new Usuario();
		Administrador administrador = new Administrador("mail@gmail.com", 1, "Seba");
		usuarioAdmin.setTipoDeUsuario(administrador);
		
		
		List<PuntoDeInteres> lista=usuarioAdmin.busquedaDePuntosDeInteres("GCP Comuna 1");
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		i = lista.iterator();
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			usuarioAdmin.eliminarPoi(n); 
		}
		
		assertTrue(lista.size()==0);
	}
}
