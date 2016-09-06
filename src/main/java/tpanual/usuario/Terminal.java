package tpanual.usuario;

public class Terminal extends TipoDeUsuario {

	private String nombre;
	private int id;
	private Estado estado;
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	public int getId() {
		return id;
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void activar() {
		// TODO Auto-generated method stub
		//Estado estadoNuevo = new FuncionesActivas();
		this.setEstado(estado);
	}
	
	@Override
	public void desactivar() {
		// TODO Auto-generated method stub
		//Estado estadoNuevo = new FuncionesNoActivas();
		this.setEstado(estado);
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void buscar(){
		
		this.estado.buscar();
		
	}
}
