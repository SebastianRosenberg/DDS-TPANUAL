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
	private MementoUsuarios memento;
	
	public ActualizarAcciones(List<ValorPrioridades> lista){
		listaCambios=lista;
	}

	@Override
	public RespuestaProceso procesar() {

			Iterator<ValorPrioridades> it = listaCambios.iterator();
			while (it.hasNext()){
				ValorPrioridades valPr = it.next();
				if(valPr.getUser()==null){
						return new RespuestaProceso(EstadoResultado.ERROR);
					}
				}
			this.memento=GestorDeUsuarios.getInstance().obtenerMemento();
			GestorDeUsuarios.getInstance().darPrivilegioAGrupo(listaCambios);
			return new RespuestaProceso(EstadoResultado.OK, "Modificaciones realizadas");	

	}

	public void desHacerCambios(){
		if(this.memento.esValido(GestorDeUsuarios.getInstance().getStringValidezMD5())){
			GestorDeUsuarios.getInstance().aplicarMemento(this.memento);
		}else{
			System.out.println("No se pueden deshacer los cambios");
		}
	}
}
