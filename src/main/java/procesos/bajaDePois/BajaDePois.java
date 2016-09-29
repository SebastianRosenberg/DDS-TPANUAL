package procesos.bajaDePois;

import java.io.FileNotFoundException;
import java.util.Iterator;
import procesos.AdministradorDeProcesos.EstadoResultado;
import java.util.List;

import org.joda.time.DateTime;

import administrador.AdministradorDePoi;
import procesos.Proceso;
import procesos.RespuestaProceso;
import tpanual.main.poi.PuntoDeInteres;

public class BajaDePois extends Proceso{
	
	List<SolicitudBajaJson> pois;

	@Override
	public RespuestaProceso procesar() {
		try{
			this.pois=ParserDeBajas.obtenerBajas();
			
		}catch(ArchivoBajasException a){
			a.printStackTrace();
			return new RespuestaProceso(EstadoResultado.ERROR, a.getMessage());
		}catch(FileNotFoundException f){
			f.printStackTrace();
			return new RespuestaProceso(EstadoResultado.ERROR, f.getMessage());
		}
		RespuestaProceso rp;
		try{
			Iterator<SolicitudBajaJson> it=pois.iterator();
			while(it.hasNext()){
				SolicitudBajaJson s=it.next();
				List<PuntoDeInteres> l = buscarPoi(s.getBusqueda());
				Iterator<PuntoDeInteres> it2=l.iterator();
				while (it2.hasNext()){
					PuntoDeInteres p=it2.next();
					p.setDadoDeBaja(true);
					p.setFechaBaja(new DateTime(s.getFecha()));
				}
			}
			rp=new RespuestaProceso(EstadoResultado.OK);
		}catch(RuntimeException r){
			rp=new RespuestaProceso(EstadoResultado.ERROR, r.getMessage());
		}
		return rp;
	}
	
	public BajaDePois(){
		
	}
	
	private List<PuntoDeInteres> buscarPoi(String s){
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(s);
	}

}
