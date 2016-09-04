package tpanual.usuario;

public abstract class TipoDeUsuario {

	public abstract String getNombre();
	public abstract int getId();
	public abstract String getEmail();
	public abstract void activar(); 
	public abstract void desactivar();
	
}
