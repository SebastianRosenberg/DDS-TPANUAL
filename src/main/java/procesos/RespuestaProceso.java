package procesos;

import java.util.List;

import procesos.AdministradorDeProcesos.EstadoResultado;
import tpanual.main.poi.PuntoDeInteres;

public class RespuestaProceso {
	
	private EstadoResultado estado;
	private List<PuntoDeInteres> poisAfectados;
	private String mensajeError;
	
	public RespuestaProceso(EstadoResultado estado){
		this.estado=estado;
	}
	public RespuestaProceso(EstadoResultado estado, String error){
		this.estado=estado;
		this.mensajeError=error;
	}
	
	public RespuestaProceso agregar(RespuestaProceso r){
		if (r.estado==EstadoResultado.ERROR)
			this.estado=EstadoResultado.ERROR;
		
		if (r.poisAfectados!=null){
			if (this.poisAfectados!=null)
				this.poisAfectados.addAll(r.poisAfectados);
			else
				this.poisAfectados=r.poisAfectados;
		}
		
		return this;
	}

	public EstadoResultado getEstado() {
		return estado;
	}

	public List<PuntoDeInteres> getPoisAfectados() {
		return poisAfectados;
	}

	public void setPoisAfectados(List<PuntoDeInteres> poisAfectados) {
		this.poisAfectados = poisAfectados;
	}

	public String getMensajeError() {
		return mensajeError;
	}
}
