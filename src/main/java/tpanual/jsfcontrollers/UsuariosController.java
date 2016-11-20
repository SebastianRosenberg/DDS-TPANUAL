package tpanual.jsfcontrollers;

import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class UsuariosController {
	private boolean esAdmin;
	
	public boolean buscarUsuario(String usuario, String password) {

		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		Usuario usuarioBd;

		usuarioBd = gestor.loguearUsuario(usuario, password);
		
		if (usuarioBd != null) {
			this.setEsAdmin(usuarioBd.isAdministrador());
			return true;
		} else {

			return false;
		}
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public boolean esAdministrador (){
		
		return esAdmin;
	}
}
