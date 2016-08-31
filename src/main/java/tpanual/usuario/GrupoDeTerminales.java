package tpanual.usuario;

import java.util.ArrayList;
import java.util.List;

public class GrupoDeTerminales {

	private List<Usuario> usuarios;
	
	public GrupoDeTerminales(){
	
		usuarios = new ArrayList<Usuario>();
		
	}
	
	public void agregarUsuario (Usuario usuarioEntrada){
		
		usuarios.add(usuarioEntrada);
		
	}
}
