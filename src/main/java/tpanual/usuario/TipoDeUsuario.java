package tpanual.usuario;

import java.util.List;

import tpanual.main.poi.PuntoDeInteres;

public abstract class TipoDeUsuario {

	public abstract String getNombre();
	public abstract int getId();
	public abstract String getEmail();
	public abstract void activar(); 
	public abstract void desactivar();
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x);
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test);
	
}
