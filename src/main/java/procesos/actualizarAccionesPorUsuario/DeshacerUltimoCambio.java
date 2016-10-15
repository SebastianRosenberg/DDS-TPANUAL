package procesos.actualizarAccionesPorUsuario;


import procesos.Proceso;
import procesos.RespuestaProceso;
import procesos.AdministradorDeProcesos.EstadoResultado;
import tpanual.seguridad.GestorDeUsuarios;


public class DeshacerUltimoCambio extends Proceso{
	
	@Override
	public RespuestaProceso procesar() {

		GestorDeUsuarios.getInstance().deshacerUltimoCambioDePermisos();
		return new RespuestaProceso(EstadoResultado.OK, "Se deshizo la ultima actualizacion de acciones por usuario");	

	}
	
}