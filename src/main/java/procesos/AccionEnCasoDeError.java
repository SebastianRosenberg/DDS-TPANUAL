package procesos;

import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public abstract class AccionEnCasoDeError{
	abstract public RespuestaProceso RealizarAccion(Usuario usuario, Proceso proceso, RespuestaProceso respuesta) throws AccionNoPermitida;
}