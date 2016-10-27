package tpanual.usuario;

import java.util.List;

import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;

public abstract class Estado {

	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test);
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x);
	public abstract PuntoDeInteres masInformacion(Usuario user, Integer id);
	public abstract List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion,
			String string2, String string3);
	public abstract List<PoiInfoBasica> realizarBusqueda(String x);
	public abstract List<PoiInfoBasica> realizarBusqueda(String x, boolean test);
	public abstract List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion,
			String palabraClave, String coincDeTipo);
	
}
