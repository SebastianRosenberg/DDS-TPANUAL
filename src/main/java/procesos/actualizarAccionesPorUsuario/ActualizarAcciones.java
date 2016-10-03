package procesos.actualizarAccionesPorUsuario;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import procesos.Proceso;
import procesos.RespuestaProceso;
import procesos.AdministradorDeProcesos.EstadoResultado;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.seguridad.ValorPrioridades;

public class ActualizarAcciones extends Proceso{
	
	private List<ValorPrioridades> listaCambios;
	
	public ActualizarAcciones(List<ValorPrioridades> lista){
		listaCambios=lista;
	}

	@Override
	public RespuestaProceso procesar() {

			int c=0;
			GestorDeUsuarios.getInstance().darPrivilegioAGrupo(listaCambios);
			Iterator<ValorPrioridades> it = listaCambios.iterator();
			while (it.hasNext()){
				c++;
				ValorPrioridades valPr = it.next();
				if(valPr.getUser()==null)
					{
						return new RespuestaProceso(EstadoResultado.ERROR, "El Usuario número"+ c +"es inválido");
					}
				GestorDeUsuarios.getInstance().darPrivilegioUsuario(valPr);
				}
			return new RespuestaProceso(EstadoResultado.OK, "Modificaciones realizadas");	

	}

	
}
