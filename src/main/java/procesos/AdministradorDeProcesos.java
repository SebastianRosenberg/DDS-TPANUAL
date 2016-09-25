package procesos;

import java.util.List;

import org.joda.time.DateTime;

import tpanual.usuario.Usuario;

public class AdministradorDeProcesos {
	
	enum EstadoResultado{
		OK,
		ERROR
	}
	class ProcesoEjecutado{
		
		Proceso proceso;
		Usuario usuario;
		DateTime inicio;
		DateTime fin;
		RespuestaProceso respuesta;
		EstadoResultado resultado;
		
		public ProcesoEjecutado(
					Proceso proceso,
					Usuario usuario,
					DateTime inicio,
					DateTime fin,
					RespuestaProceso respuesta,
					EstadoResultado resultado
				)
		{
			this.proceso = proceso;
			this.usuario = usuario;
			this.inicio = inicio;
			this.fin = fin;
			this.respuesta = respuesta;
			this.resultado = resultado;
		}
		
	}
	
	List<ProcesoEjecutado> procesosEjecutados;
	List<Proceso> procesosDisponibles;
	
	
	public void AgregarProcesoDisponible(Proceso proceso){
		procesosDisponibles.add(proceso);
	}
	
	public void QuitarProcesoDisponible(Proceso proceso){
		procesosDisponibles.remove(proceso);
	}
	
	
	public RespuestaProceso EjecutarProceso(Proceso proceso) throws Exception{
		RespuestaProceso respuesta;
		EstadoResultado resultado;
		Usuario usuario = null;
		if(procesosDisponibles.contains(proceso)){
			
			DateTime inicio = DateTime.now();
			try
			{
				respuesta = proceso.procesar();
				resultado = EstadoResultado.OK;
			}
			catch(Exception ex)
			{
				respuesta = null;
				resultado = EstadoResultado.ERROR;
			}
			DateTime fin = DateTime.now();
			
			AgregarProcesoEjecutado(proceso, usuario, inicio, fin, respuesta, resultado);
			
			return respuesta;
		}
		else
		{
			throw new Exception("El proceso no esta permitido");
		}
		
		
	}

	private void AgregarProcesoEjecutado(Proceso proceso, Usuario usuario,
			DateTime inicio, DateTime fin, RespuestaProceso respuesta,
			EstadoResultado resultado) {
		
		ProcesoEjecutado procesoEj =
				new ProcesoEjecutado(proceso, usuario, inicio, fin, respuesta, resultado);
		
		procesosEjecutados.add(procesoEj);
		
		
		
	}

}
