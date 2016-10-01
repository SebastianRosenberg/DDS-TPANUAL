package tpanual.usuario;

import java.util.List;

import tpanual.main.Direccion;
import tpanual.main.poi.PuntoDeInteres;

public abstract class TipoDeUsuario {

	public abstract String getNombre();
	public abstract int getId();
	public abstract String getEmail();
	public abstract void activar(); 
	public abstract void desactivar();
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x);
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test);
	public abstract boolean modificarPoi(PuntoDeInteres poi);
	public abstract void agregarPoi(PuntoDeInteres poi);
	public abstract boolean eliminarPoi(PuntoDeInteres poi);
	public abstract Usuario loguear(Usuario usuario, String password,Usuario terminal);
	public abstract Usuario desloguear(Usuario usuario);
	public abstract void notificar();
	public abstract PuntoDeInteres masInformacion(Usuario user, Integer id);
	public abstract List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion, 
					String string2, String string3);
	
}
