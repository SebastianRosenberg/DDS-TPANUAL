package procesos;

import tpanual.usuario.Usuario;

public class Nada extends AccionEnCasoDeError{

	@Override
	public RespuestaProceso RealizarAccion(Usuario usuario, Proceso proceso, RespuestaProceso respuesta) throws AccionNoPermitida {
		return respuesta;
	}
	
}