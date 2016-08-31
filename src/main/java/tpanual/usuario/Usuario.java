package tpanual.usuario;

public class Usuario {

	private TipoDeUsuario tipoDeUsuario;
	
	
	public String getUsuario(){
		
		return tipoDeUsuario.getNombre();
	}
	
	public int getId(){
		return tipoDeUsuario.getId();
	}
}