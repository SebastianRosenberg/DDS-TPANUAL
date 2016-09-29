package procesos;

import java.util.List;

import procesos.actualizacionLC.ActualizacionDeLocalesComerciales;
import procesos.bajaDePois.BajaDePois;
import tpanual.main.poi.PuntoDeInteres;

public class ProcesosFactory {
	public static Proceso getBajaPoi(List<PuntoDeInteres> lista){
		Proceso proceso = new BajaDePois(lista);
		return proceso;
	}
	public static Proceso getActualizacionLocalComercial(){
		Proceso proceso = new ActualizacionDeLocalesComerciales();
		return proceso;
	}
	public static Proceso getProcesoMultipleComposite(List<Proceso> l){
		Proceso proceso = new ProcesoMultipleComposite(l);
		return proceso;
	}
}
