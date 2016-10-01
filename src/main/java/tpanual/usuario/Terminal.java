package tpanual.usuario;

import java.util.List;

import tpanual.main.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;

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
		Estado estadoNuevo = new Activo();
		this.setEstado(estadoNuevo);
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
	
	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x) {
		// TODO Auto-generated method stub
		return this.estado.busquedaDePuntosDeInteres(x);
	}
	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
		// TODO Auto-generated method stub
		return this.estado.busquedaDePuntosDeInteres(x, test);
	}



	@Override
	public boolean modificarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void agregarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean eliminarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Terminal(String nombre,Estado estado,int Id){
		
		this.setEstado(estado);
		this.setId(Id);
		this.setNombre(nombre);
		
	}

	public void setNombre(String nombre) {
	
		this.nombre = nombre;
	
	}

	public void setId(int id) {
	
		this.id = id;
	
	}
	
	
	public Usuario loguear(Usuario usuario, String password,Usuario terminal)
	{
		
		Usuario admin = GestorDeUsuarios.getInstance().logueoComoAdmin(usuario, password, terminal);
		return admin;
		
	}
	

	@Override
	public Usuario desloguear(Usuario usuario) {
		// TODO Auto-generated method stub
		System.out.println("No se ha podido desloguear debido a que no se encontraba loguado en un principio");
		return null;
	}
	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PuntoDeInteres masInformacion(Usuario user, Integer id) {
		return this.estado.masInformacion(user, id);
	}
	@Override
	public List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion,
			String string2, String string3) {
		return this.estado.busquedaAvanzada(usuarioAProbar, string, direccion, string2, string3);
	}
}
