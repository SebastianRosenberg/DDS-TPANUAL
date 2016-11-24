package tpanual.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

public class UsuarioHibernateTest {
	static HibernateFactorySessions hs = new HibernateFactorySessions();
	
	@Test
	public void persistirUsuarioTest(){
		
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		Usuario nuevoUsuarioAdmin = gestor.crearAdministrador("unAdminDePrueba", "unAdminDePrueba@hotmail.com","peras");
		assertTrue(hs.obtenerUsuario(nuevoUsuarioAdmin.getId())!=null);
		
	}

	@Test
	public void eliminarUsuarioTest(){
		
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalNoActivo("unaTerminalDePrueba");
		
		//Elimino
		int id = nuevoUsuarioTerminal.getId();
		hs.eliminarObjetoBd(nuevoUsuarioTerminal);

		//Compruebo que al buscarlo no exista
		Usuario usuarioBorrado = hs.obtenerUsuario(id);
		assertNull(usuarioBorrado);
		
	}
	
	
}
