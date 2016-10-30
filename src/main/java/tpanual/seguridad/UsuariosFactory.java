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
		id++;
		return administrador;
		
	}
	
	static Usuario getUsuarioTerminalActivo(String nombre){
		
		Activo estadoActivo = new Activo();
		Terminal terminal = new Terminal(nombre, estadoActivo, id);
		id++;
		return terminal;
		
	}
	
	static Usuario getUsuarioTerminalNoActivo(String nombre){
		NoActivo estadoNoActivo = new NoActivo();
		Terminal terminal = new Terminal(nombre, estadoNoActivo, id);
		id++;
		return terminal;
		
	}
	
}
