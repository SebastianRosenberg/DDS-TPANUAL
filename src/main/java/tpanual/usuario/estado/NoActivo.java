package tpanual.usuario.estado;

import java.util.List;

import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;

public class NoActivo extends Estado{

	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
		return null;
	}

	@Override
	public PuntoDeInteres masInformacion(Integer id) {
		return null;
	}

	@Override
	public List<PuntoDeInteres> busquedaAvanzada(String x, boolean test) {
		return null;
	}

	@Override
	public List<PoiInfoBasica> busquedaBasica(String x, boolean test) {
		return null;
	}

}
