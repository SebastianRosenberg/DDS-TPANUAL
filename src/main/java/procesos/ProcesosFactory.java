package procesos;

import java.util.List;

import procesos.actualizacionLC.ActualizacionDeLocalesComerciales;
import procesos.actualizarAccionesPorUsuario.ActualizarAcciones;
import procesos.bajaDePois.BajaDePois;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.ValorPrioridades;

public class ProcesosFactory {
	public static Proceso getBajaPoi(){
		Proceso proceso = new BajaDePois();
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
	public static Proceso getActualizacionAccionesUsuario(List<ValorPrioridades> list){
		Proceso proceso = new ActualizarAcciones(list);
		return proceso;
		
	}
}
