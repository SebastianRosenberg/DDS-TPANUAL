package tpanual.usuario;

import java.util.List;

import tpanual.main.poi.PuntoDeInteres;

public class Usuario {

	private TipoDeUsuario tipoDeUsuario;
	
	public String getEmail(){
		
		return tipoDeUsuario.getEmail();
	}
	
	public String getUsuario(){
		
		return tipoDeUsuario.getNombre();
	}
	
	public int getId(){
		return tipoDeUsuario.getId();
	}
	
	
	public void activar(){
		
		tipoDeUsuario.activar();
	} 
	
	public void desactivar(){
		tipoDeUsuario.desactivar();
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x){
		return tipoDeUsuario.busquedaDePuntosDeInteres(x);
		
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test){
		return tipoDeUsuario.busquedaDePuntosDeInteres(x, test);
		
	}
	
	public boolean modificarPoi(PuntoDeInteres poi){
		return tipoDeUsuario.modificarPoi(poi);
		
	}
	
	public void agregarPoi(PuntoDeInteres poi){
		tipoDeUsuario.agregarPoi(poi);
	}
	
	public boolean eliminarPoi(PuntoDeInteres poi){
		return tipoDeUsuario.eliminarPoi(poi);
		
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	
	public Usuario(TipoDeUsuario tipo){
		
		this.setTipoDeUsuario(tipo);
		
	}
	
	
}