package tpanual.usuario.estado;

import java.util.List;

import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;

public abstract class Estado {
	
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test);
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x){
		return busquedaDePuntosDeInteres(x, true);
	}
	public abstract PuntoDeInteres masInformacion(Integer id);
	
	public abstract List<PuntoDeInteres> busquedaAvanzada(String x, boolean test);
	public List<PuntoDeInteres> busquedaAvanzada(String x){
		return busquedaAvanzada(x, true);
	}
	
	public abstract List<PuntoDeInteres> busquedaBasica(String x, boolean test);
	public List<PuntoDeInteres> realizarBusquedabasica(String x){
		return busquedaBasica(x, true);
	}
	
	
}
