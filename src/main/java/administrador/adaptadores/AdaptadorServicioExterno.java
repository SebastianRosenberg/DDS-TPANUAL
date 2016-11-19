package administrador.adaptadores;

import java.util.List;

import tpanual.main.poi.PuntoDeInteres;

public interface AdaptadorServicioExterno {
	public List<PuntoDeInteres> buscar(String x, String x2);
}