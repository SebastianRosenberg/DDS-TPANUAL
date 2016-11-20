package procesos.actualizacionLC;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import administrador.AdministradorDePoi;
import procesos.Proceso;
import procesos.RespuestaProceso;
import procesos.AdministradorDeProcesos.EstadoResultado;
import tpanual.main.poi.LocalComercial;
import tpanual.main.poi.PalabraClave;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.ParseadorDeTextos;

public class ActualizacionDeLocalesComerciales extends Proceso {
	
	
	@Override
	public RespuestaProceso procesar() {
		try {
			List<ActualizacionLocalComercial> lista=ParseadorDeTextos.parsearTexto();
			Iterator<ActualizacionLocalComercial> it = lista.iterator();
			while (it.hasNext()){
				ActualizacionLocalComercial local=it.next();
				PuntoDeInteres poi=AdministradorDePoi.getInstance().obtenerPoiPorId(local.getPoi().getId());
				if (!(poi.getTipo() instanceof LocalComercial)){
					return new RespuestaProceso(EstadoResultado.ERROR, "El Punto de interes ingresado: " + poi.getId() + " no es un Local comercial");
				}
				poi.setPalabrasClavesList(PalabraClave.getListaPalabrasClave(local.getPalabrasNuevas()));
				AdministradorDePoi.getInstance().modificarPoi(poi);
			}
			return new RespuestaProceso(EstadoResultado.OK, "Modificaciones realizadas");
		} catch (IOException e) {
			e.printStackTrace();
			return new RespuestaProceso(EstadoResultado.ERROR, e.getMessage());
		}
	}

}
