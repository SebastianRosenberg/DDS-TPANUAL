package procesos;

import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Email;

public class EnviarMail extends AccionEnCasoDeError{

	@Override
	public RespuestaProceso RealizarAccion(Usuario usuario, Proceso proceso, RespuestaProceso respuesta) throws AccionNoPermitida{
		
		if (usuario.isAdministrador()){
			Administrador a = (Administrador) usuario;
			Email EnviadorMail = new Email(a.getEmail(),
					"Error al ejecutar proceso", respuesta.getMensajeError());
		}else{
			throw new AccionNoPermitida();
		}
		
	
		return respuesta;
	}
	
}