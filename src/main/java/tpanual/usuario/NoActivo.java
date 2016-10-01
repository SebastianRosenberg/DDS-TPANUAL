package tpanual.usuario;

import java.util.List;

import tpanual.main.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;

public class NoActivo extends Estado{

	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PuntoDeInteres masInformacion(Usuario user, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion,
			String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PoiInfoBasica> realizarBusqueda(String x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PoiInfoBasica> realizarBusqueda(String x, boolean test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion,
			String palabraClave, String coincDeTipo) {
		// TODO Auto-generated method stub
		return null;
	}

}
