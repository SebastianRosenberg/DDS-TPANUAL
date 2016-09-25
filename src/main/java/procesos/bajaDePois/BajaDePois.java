package procesos.bajaDePois;

import java.util.Iterator;
import procesos.AdministradorDeProcesos.EstadoResultado;
import java.util.List;

import procesos.Proceso;
import procesos.RespuestaProceso;
import tpanual.main.poi.PuntoDeInteres;

public class BajaDePois extends Proceso{
	
	List<PuntoDeInteres> pois;

	@Override
	public RespuestaProceso procesar() {
		RespuestaProceso rp;
		try{
			Iterator<PuntoDeInteres> it=pois.iterator();
			while(it.hasNext()){
				it.next().setDadoDeBaja(true);
			}
			rp=new RespuestaProceso(EstadoResultado.OK);
			rp.setPoisAfectados(pois);
		}catch(RuntimeException r){
			rp=new RespuestaProceso(EstadoResultado.ERROR, r.getMessage());
		}
		return rp;
	}
	
	public BajaDePois(List<PuntoDeInteres> pois){
		this.pois=pois;
	}

}
