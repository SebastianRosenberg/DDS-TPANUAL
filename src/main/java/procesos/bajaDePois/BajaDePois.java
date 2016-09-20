package procesos.bajaDePois;

import java.util.Iterator;
import java.util.List;

import procesos.Proceso;
import procesos.RespuestaProceso;
import tpanual.main.poi.PuntoDeInteres;

public class BajaDePois extends Proceso{
	
	List<PuntoDeInteres> pois;

	@Override
	public RespuestaProceso procesar() {
		Iterator<PuntoDeInteres> it=pois.iterator();
		while(it.hasNext()){
			it.next().setDadoDeBaja(true);
		}
		RespuestaProceso rp=new RespuestaProceso();
		return rp;
	}
	
	public BajaDePois(List<PuntoDeInteres> pois){
		this.pois=pois;
	}

}
