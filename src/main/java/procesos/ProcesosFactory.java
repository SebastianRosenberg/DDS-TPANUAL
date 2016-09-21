package procesos;

import java.util.List;

import procesos.bajaDePois.BajaDePois;
import tpanual.main.poi.PuntoDeInteres;

public class ProcesosFactory {
	public static Proceso getBajaPoi(List<PuntoDeInteres> lista){
		Proceso proceso = new BajaDePois(lista);
		return proceso;
	}
}
