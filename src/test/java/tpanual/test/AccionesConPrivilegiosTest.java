package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.ValorPrioridades;
import tpanual.usuario.Usuario;

public class AccionesConPrivilegiosTest {

	@Test
	public void masInformacionTest() {
		
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
		
		Usuario usuarioAProbar = GestorDeUsuarios.getInstance().crearTerminalActivo("Tomas");
		
		PuntoDeInteres poiBuscado;
		
		//Fin Del Seteo
		
		System.out.println("intento buscar m�s info sin tener permiso");
		
		usuarioAProbar.masInformacion(usuarioAProbar, poi.getId());
		
		GestorDeUsuarios.getInstance().darPrivilegioMasInfo (usuarioAProbar);
		
		poiBuscado = usuarioAProbar.masInformacion(usuarioAProbar, poi.getId());
		
		assertTrue(poiBuscado==poi);
		
	}
	
	@Test
	public void otorgarPrivilegiosTest()
	{
		Usuario usuarioAProbar1 = GestorDeUsuarios.getInstance().crearTerminalActivo("Alan");
		Usuario usuarioAProbar2 = GestorDeUsuarios.getInstance().crearTerminalActivo("Nahuel");
		Usuario usuarioAProbar3 = GestorDeUsuarios.getInstance().crearTerminalActivo("Sebastian");
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar2));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar2));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar3));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar3));
		
		ValorPrioridades valor = new ValorPrioridades();
		valor.setUser(usuarioAProbar1);
		valor.setPrioridad1(true);
		valor.setPrioridad2(false);
		ValorPrioridades valor2 = new ValorPrioridades();
		valor2.setUser(usuarioAProbar2);
		valor2.setPrioridad1(false);
		valor2.setPrioridad2(true);
		ValorPrioridades valor3 = new ValorPrioridades();
		valor3.setUser(usuarioAProbar3);
		valor3.setPrioridad1(true);
		valor3.setPrioridad2(true);
		List<ValorPrioridades> lista = new ArrayList<ValorPrioridades>();
		lista.add(valor);
		lista.add(valor2);
		lista.add(valor3);
		
		GestorDeUsuarios.getInstance().darPrivilegioAGrupo(lista);
		
		
		assertTrue(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar2));
		assertTrue(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar2));
		assertTrue(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar3));
		assertTrue(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar3));
		
		
		
		/*Revisar esto, guardo una copia de las listas de privilegios antes de hacer el ULTIMO cambio
		* Si realizo dos cambios seguidos, no puedo ir hacia atras de ambos cambios
		*/
		GestorDeUsuarios.getInstance().deshacerUltimoCambioDePermisos();
		
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar1));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar2));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar2));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(usuarioAProbar3));
		assertFalse(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(usuarioAProbar3));
		
	}
	
}
