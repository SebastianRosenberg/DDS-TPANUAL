package tpanual.seguridad;

import java.util.Hashtable;

import tpanual.factory.UsuariosFactory;
import tpanual.usuario.Usuario;

public class GestorDeTerminal {
	private static GestorDeTerminal instance;
	private static Hashtable<Integer, Usuario> usuarios;

	public static GestorDeTerminal getInstance(){
		if (instance==null)
			instance=new GestorDeTerminal();
		return instance;
	}
	
	public Usuario crearTerminal(String nombre, int id)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioTerminalActivo(nombre, id);
		usuarios.put(id, nuevoUsuario);
		return nuevoUsuario;	
	}
	
	public Hashtable<Integer, Usuario> getUsuarios() {
		return usuarios;
	}
	
}