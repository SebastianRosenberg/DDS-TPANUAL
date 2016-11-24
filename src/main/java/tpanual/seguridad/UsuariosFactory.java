package tpanual.seguridad;

import tpanual.usuario.Administrador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.usuario.estado.Activo;
import tpanual.usuario.estado.NoActivo;


public class UsuariosFactory {
	
	static Usuario getUsuarioAdministrador(String nombre,String email, String password){		
		
		Administrador administrador = new Administrador(email, nombre, password);
		return administrador;
		
	}
	
	static Usuario getUsuarioTerminalActivo(String nombre){
		
		Activo estadoActivo = new Activo();
		Terminal terminal = new Terminal(nombre, estadoActivo);
		return terminal;
		
	}
	
	static Usuario getUsuarioTerminalNoActivo(String nombre){
		NoActivo estadoNoActivo = new NoActivo();
		Terminal terminal = new Terminal(nombre, estadoNoActivo);
		return terminal;
		
	}

	
}
