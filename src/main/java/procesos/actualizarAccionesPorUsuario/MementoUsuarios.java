package procesos.actualizarAccionesPorUsuario;

import java.util.List;
import java.util.Map;

import tpanual.usuario.Usuario;

public class MementoUsuarios {
	private Map<String, Usuario> usuarios;
	private String validez;
	
	public MementoUsuarios(Map<String, Usuario> u, String validez){
		this.usuarios=u;
		this.validez = validez;
	}

	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}


	public String getValidez() {
		return validez;
	}
	
	public boolean esValido(String validez){
		return this.validez.equals(validez);
	}
	
}
