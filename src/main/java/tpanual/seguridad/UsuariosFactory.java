package tpanual.seguridad;

import tpanual.usuario.Activo;
import tpanual.usuario.Administrador;
import tpanual.usuario.NoActivo;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;

public class UsuariosFactory {
	
	private static int id = 1;


	
	static Usuario getUsuarioAdministrador(String nombre,String email){		
		
		Administrador administrador = new Administrador(email, id, nombre);
		Usuario usuarioAdministrador = new Usuario(administrador);
		id++;
		return usuarioAdministrador;
		
	}
	
	static Usuario getUsuarioTerminalActivo(String nombre){
		
		Activo estadoActivo = new Activo();
		Terminal terminal = new Terminal(nombre, estadoActivo, id);
		Usuario usuarioTerminal = new Usuario(terminal);
		id++;
		return usuarioTerminal;
		
	}
	
	static Usuario getUsuarioTerminalNoActivo(String nombre){
		NoActivo estadoNoActivo = new NoActivo();
		Terminal terminal = new Terminal(nombre, estadoNoActivo, id);
		Usuario usuarioTerminal = new Usuario(terminal);
		id++;
		return usuarioTerminal;
		
	}
	
}
