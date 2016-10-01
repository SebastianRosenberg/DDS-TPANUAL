package tpanual.usuario;

import java.util.List;

import tpanual.main.Direccion;
import tpanual.main.poi.PuntoDeInteres;

public abstract class Estado {

	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test);
	public abstract List<PuntoDeInteres> busquedaDePuntosDeInteres(String x);
	public abstract PuntoDeInteres masInformacion(Usuario user, Integer id);
	public abstract List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion,
			String string2, String string3);
	
}
