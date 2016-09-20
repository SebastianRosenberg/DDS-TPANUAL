package procesos;

import java.util.ArrayList;
import java.util.List;

public class ProcesoMultipleComposite extends Proceso{
	private List<Proceso> procesos;
	
	public ProcesoMultipleComposite(List<Proceso> lista){
		procesos=lista;
	}
	

	@Override
	public RespuestaProceso procesar() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
