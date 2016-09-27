package tpanual.usuario;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.Instant;

import administrador.SesionBusqueda;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.temporizador.Temporizador;

public class Usuario {

	private TipoDeUsuario tipoDeUsuario;
//	
//															/* confirmar esto
//															 */
//	private TipoDeUsuario tipoDeUsuarioOriginal;
//	
//	public TipoDeUsuario getTipoDeUsuarioOriginal(){
//		return tipoDeUsuarioOriginal;
//	}
//															////////////////////
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
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar){
	
		SesionBusqueda sBusqueda = new SesionBusqueda();
		Temporizador temporizador = new Temporizador();
		Instant inicio = temporizador.TiempoInicioBusqueda();

		List<PuntoDeInteres> pois =tipoDeUsuario.busquedaDePuntosDeInteres(strABuscar); 
		sBusqueda.setPois(pois);
		
		Duration duracion = temporizador.LapsoBusqueda(inicio);
		
		sBusqueda.setDuracion(duracion);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar, boolean test){
		SesionBusqueda sBusqueda = new SesionBusqueda();
		Temporizador temporizador = new Temporizador();
		Instant inicio = temporizador.TiempoInicioBusqueda();

		List<PuntoDeInteres> pois =tipoDeUsuario.busquedaDePuntosDeInteres(strABuscar, test); 
		sBusqueda.setPois(pois);
		
		Duration duracion = temporizador.LapsoBusqueda(inicio);
		
		sBusqueda.setDuracion(duracion);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
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
//															/* confirmar esto
//															 */
//		this.tipoDeUsuarioOriginal= tipo;
//		
	}
	
	public Usuario logueo(Usuario admin,String pass,Usuario term){
		
		return this.tipoDeUsuario.loguear(admin, pass, term);
	}
	
	public String toString(){
		return Integer.valueOf(tipoDeUsuario.getId()) + "_" + tipoDeUsuario.getNombre();
	}
	
	
}