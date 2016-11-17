package tpanual.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

public class UsuarioHibernateTest {
	static HibernateFactorySessions hs;
	static int idUsuarioEliminado;
	static int idUsuarioModificado;
	
	@Test
	public void persistirUsuarioTest(){
		
		hs = new HibernateFactorySessions();
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance ();
		Usuario nuevoUsuarioAdmin =gestor.crearAdministrador("sebas", "mailsebas@hotmail.com","peras");
		//int idUsuarioBd = hs.add(nuevoUsuarioAdmin);
		//assertTrue(hs.obtenerUsuario(idUsuarioBd).getId() == idUsuarioBd);
		
	}

	@Test
	public void eliminarUsuarioTest(){
		hs = new HibernateFactorySessions();
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalNoActivo("terminalUrquiza");
		idUsuarioEliminado = hs.add(nuevoUsuarioTerminal);
		nuevoUsuarioTerminal.setId(idUsuarioEliminado);
		
		//Elimino
		hs.eliminarObjetoBd(nuevoUsuarioTerminal);

		//Compruebo que al buscarlo no exista
		Usuario usuarioBorrado = hs.obtenerUsuario(idUsuarioEliminado);
		assertNull(usuarioBorrado);
		
	}
	
	
}
