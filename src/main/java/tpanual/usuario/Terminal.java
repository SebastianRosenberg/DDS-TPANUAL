package tpanual.usuario;

import java.util.List;

import administrador.Mapa;
import tpanual.main.poi.PuntoDeInteres;

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
	
	
	
//	//prueba seguridad, se lo doy al mapa, pero puede ser que se mueva a la clase seguridad y que haga de pasamanos
//	public Administrador Loguear(String usuario, String password)
//	{
//		Mapa mapa = Mapa.getInstance();
//		Administrador admin = mapa.autentifica(usuario, password);
//		return admin;
//		
//	}
}
