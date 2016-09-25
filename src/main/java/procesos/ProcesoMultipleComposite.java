package procesos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProcesoMultipleComposite extends Proceso{
	private List<Proceso> procesos;
	
	public ProcesoMultipleComposite(List<Proceso> lista){
		procesos=lista;
	}
	

	@Override
	public RespuestaProceso procesar() {
		if (procesos!=null){
			Iterator<Proceso> it=procesos.iterator();
			RespuestaProceso r=it.next().procesar();
			while (it.hasNext()){
				r.agregar(it.next().procesar());
			}
			return r;
		}else{
			return null;
		}
	}
}
