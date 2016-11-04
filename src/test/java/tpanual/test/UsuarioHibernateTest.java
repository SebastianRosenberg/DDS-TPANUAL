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
		int idUsuarioBd;
		int idUsuarioTBd;
		Usuario usuarioBD = null;
		//Usuario usuarioTBd = null;
		Usuario nuevoUsuarioAdmin =gestor.crearAdministrador("sebas", "mailsebas@hotmail.com","peras");
		Usuario nuevoUsuarioTerminal = gestor.crearTerminalNoActivo("terminalUrquiza");
		Usuario nuevoUsuarioTerminal2 = gestor.crearTerminalNoActivo("terminalBelgrano");
		//hs.persistirObjeto(nuevoUsuarioAdmin);
		//hs.persistirObjeto(nuevoUsuarioTerminal);
		//hs.persistirObjeto(nuevoUsuarioTerminal2);
		//hs.add(nuevoUsuarioAdmin);
		
		//hs.add(nuevoUsuarioTerminal2);
		idUsuarioBd = hs.add(nuevoUsuarioAdmin);//Integer id = nuevoUsuarioAdmin.getId();
		//Integer idT1 = nuevoUsuarioTerminal.getId();
		idUsuarioTBd = nuevoUsuarioTerminal2.getId();
		//idUsuarioEliminado = nuevoUsuarioTerminal.getId();
		usuarioBD = hs.obtenerUsuario(idUsuarioBd);
		assertTrue(usuarioBD.getId() == idUsuarioBd);
		//assertTrue(nuevoUsuarioTerminal == hs.obtenerUsuarioBd(idT1));
		//assertTrue(nuevoUsuarioTerminal2 == hs.obtenerUsuarioBd(idT2));
		
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
