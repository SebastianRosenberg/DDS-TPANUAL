package tpanual.factory;

import tpanual.usuario.Activo;
import tpanual.usuario.Administrador;
import tpanual.usuario.NoActivo;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;

public class UsuariosFactory {

	public static Usuario getUsuarioAdministrador(String nombre,String email,int Id){
		
		Administrador administrador = new Administrador(email, Id, nombre);
		Usuario usuarioAdministrador = new Usuario(administrador);
		return usuarioAdministrador;
		
	}
	
	public static Usuario getUsuarioTerminalActivo(String nombre,int Id){
		
		Activo estadoActivo = new Activo();
		Terminal terminal = new Terminal(nombre, estadoActivo, Id);
		Usuario usuarioTerminal = new Usuario(terminal);
		return usuarioTerminal;
		
	}
	
	public static Usuario getUsuarioTerminalNoActivo(String nombre,int Id){
		
		NoActivo estadoNoActivo = new NoActivo();
		Terminal terminal = new Terminal(nombre, estadoNoActivo, Id);
		Usuario usuarioTerminal = new Usuario(terminal);
		return usuarioTerminal;
		
	}
	
}
