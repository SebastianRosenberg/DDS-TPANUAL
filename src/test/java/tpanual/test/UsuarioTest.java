package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import administrador.Mapa;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.UsuariosFactory;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class UsuarioTest {

	static GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	
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
		
		
		PuntoDeInteres poi1=PuntoDeInteresFactory.getParadaDeColectivo(600, 1200, "Parada de la linea ciento catorce", direccion, palabras2, "114");
		
		/*Seteo usuario*/
		Usuario nuevoUsuario = gestor.crearAdministrador("UserTest", "esuntest@gmail.com","1234cinco");
		
		/*Tareas del usuario*/
		nuevoUsuario.agregarPoi(poi);
		nuevoUsuario.agregarPoi(poi1);
		
		/*asigno a una lista el resultado de la búsqueda*/
		List<PuntoDeInteres> lista= nuevoUsuario.busquedaDePuntosDeInteres("");
		
		assertTrue(lista.contains(poi));
	}
	
	@Test
	public void administradorEliminarPoiTest(){
				
		Usuario nuevoUsuario = gestor.crearAdministrador("UserTest", "esuntest@gmail.com","1234cinco");
		
		List<PuntoDeInteres> lista=nuevoUsuario.busquedaDePuntosDeInteres("Parada de la linea ciento catorce");
		Iterator<PuntoDeInteres> i = lista.iterator();
		
		PuntoDeInteres poi1 = null;
		i = lista.iterator();
		while(i.hasNext()){	
			PuntoDeInteres n=i.next();
			nuevoUsuario.eliminarPoi(n);
			poi1 = n;
		}
		
		assertFalse(lista.contains(poi1.getId()));
	}
	
	@Test
	public void usuarioTerminalActivoBusquedaTest(){
		
		Usuario usuarioTerminalActivo = gestor.crearTerminalActivo("UserTest");
		assertTrue(usuarioTerminalActivo.busquedaDePuntosDeInteres("") != null);
	}
	
	@Test
	public void usuarioTerminalNoActivoBusquedaTest(){
		
		Usuario usuarioTerminalNoActivo = gestor.crearTerminalNoActivo("UserTest");
				
		assertTrue(usuarioTerminalNoActivo.busquedaDePuntosDeInteres("")==null);
	}
	
}
