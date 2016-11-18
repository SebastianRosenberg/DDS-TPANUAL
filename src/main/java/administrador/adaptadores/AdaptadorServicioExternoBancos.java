package administrador.adaptadores;

import java.util.List;

import tpanual.factory.InterfacesExternasFactory;
import tpanual.main.poi.PuntoDeInteres;

public class AdaptadorServicioExternoBancos implements AdaptadorServicioExterno{

	public List<PuntoDeInteres> buscar(String banco, String servicio) {
		return InterfacesExternasFactory.getComunicacionServiciosExternos().obtenerBancosPorNombreYServicio(banco, "");
	}

}
