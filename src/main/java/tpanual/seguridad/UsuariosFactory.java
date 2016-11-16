package tpanual.seguridad;

import tpanual.usuario.Administrador;
import tpanual.usuario.Terminal;
import tpanual.usuario.Usuario;
import tpanual.usuario.estado.Activo;
import tpanual.usuario.estado.NoActivo;


public class UsuariosFactory {
	
	private static int id = 1;

	
	static Usuario getUsuarioAdministrador(String nombre,String email, String password){		
		
		Administrador administrador = new Administrador(email, sumarYObtener(), nombre, password);
		return administrador;
		
	}
	
	static Usuario getUsuarioTerminalActivo(String nombre){
		
		Activo estadoActivo = new Activo();
		Terminal terminal = new Terminal(nombre, estadoActivo, sumarYObtener());
		return terminal;
		
	}
	
	static Usuario getUsuarioTerminalNoActivo(String nombre){
		NoActivo estadoNoActivo = new NoActivo();
		Terminal terminal = new Terminal(nombre, estadoNoActivo, sumarYObtener());
		return terminal;
		
	}
	/**
	 * Este metodo es sincronico, es decir no puede darse que dos instancias lo llamen al mismo tiempo.
	 * Esto garantiza copias viejas del id
	 * @return
	 */
	static synchronized int sumarYObtener(){
		id++;
		return id;
	}
	
}
