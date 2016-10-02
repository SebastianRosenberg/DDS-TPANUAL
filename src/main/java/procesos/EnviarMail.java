package procesos;

import tpanual.usuario.Usuario;
import tpanual.utilitarios.Email;

public class EnviarMail extends AccionEnCasoDeError{

	@Override
	public RespuestaProceso RealizarAccion(Usuario usuario, Proceso proceso, RespuestaProceso respuesta) {
		Email EnviadorMail = new Email(usuario.getEmail(),
                "Error al ejecutar proceso", respuesta.getMensajeError());
	
		return respuesta;
	}
	
}