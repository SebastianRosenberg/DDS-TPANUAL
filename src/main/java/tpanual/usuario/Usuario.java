package tpanual.usuario;

import java.util.List;
import org.joda.time.Duration;
import org.joda.time.Instant;

import administrador.AdministradorDePoi;
import administrador.SesionBusqueda;
import tpanual.main.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;

import tpanual.temporizador.Temporizador;

public class Usuario {

	private TipoDeUsuario tipoDeUsuario;
//	
//															
/* confirmar esto
//															 */
//	private TipoDeUsuario tipoDeUsuarioOriginal;
//	
//	public TipoDeUsuario getTipoDeUsuarioOriginal(){
//		return tipoDeUsuarioOriginal;
//	}
	//

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
		
		Instant inicio = Temporizador.TiempoInicioBusqueda();

		List<PuntoDeInteres> pois =tipoDeUsuario.busquedaDePuntosDeInteres(strABuscar); 
		sBusqueda.setPois(pois);
		
		Duration duracion = Temporizador.LapsoBusqueda(inicio);
		
		Temporizador.ChequeoLapso (duracion, this);
		
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
		Temporizador.ChequeoLapso (duracion, this);
		
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
	
	public void notificar(){
		
		this.tipoDeUsuario.notificar();
		
	}
	
	public PuntoDeInteres masInformacion(Usuario user, Integer id){
		return tipoDeUsuario.masInformacion(user, id);
		
	}

	public List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion, String string2,
			String string3) {
		return tipoDeUsuario.busquedaAvanzada(usuarioAProbar, string, direccion, string2, string3);
		
	}
	
	
	
	public List<PoiInfoBasica> realizarBusqueda(String x)
	{	
		return  tipoDeUsuario.realizarBusqueda(x);
	}

	public  List<PoiInfoBasica> realizarBusqueda(String x, boolean test)
	{
		return  tipoDeUsuario.realizarBusqueda(x,test);
	}

	public  List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion, String palabraClave, String coincDeTipo)
	{
		return  tipoDeUsuario.realizarBusquedaAvanzada(user, nombre,direccion,palabraClave,coincDeTipo);
	}
	
}