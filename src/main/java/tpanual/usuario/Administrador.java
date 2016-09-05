package tpanual.usuario;

public class Administrador extends TipoDeUsuario{

	private String nombre;
	private int id;
	private String email;
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public void activar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub
		
	}

	
}
