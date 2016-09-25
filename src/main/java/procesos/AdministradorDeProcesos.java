package procesos;

import java.util.List;

import org.joda.time.DateTime;

import tpanual.seguridad.GestorDeUsuarios;
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
		
		public ProcesoEjecutado(
					Proceso proceso,
					Usuario usuario,
					DateTime inicio,
					DateTime fin,
					RespuestaProceso respuesta
				)
		{
			this.proceso = proceso;
			this.usuario = usuario;
			this.inicio = inicio;
			this.fin = fin;
			this.respuesta = respuesta;
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
	
	public List<Proceso> GetProcesosDisponiblesParaElUsuario(Usuario usuario){
		//GestorDeUsuarios gestorUsuarios = GestorDeUsuarios.getInstance();
		
		//TODO: preguntar si es admin
		//if (ES ADMINISTRADOR)
		return procesosDisponibles;
		
		//else
		//return new List<Proceso>();
	}
	
	
	public RespuestaProceso EjecutarProceso(Proceso proceso, Usuario usuario)throws Exception{
		RespuestaProceso respuesta;
		EstadoResultado resultado;
		if(procesosDisponibles.contains(proceso)){//Esto va a haber que cambiarlo para ver si es permitido para el usuario
			
			DateTime inicio = DateTime.now();
			
				respuesta = proceso.procesar();
			
			//Aca tiene que preguntar por el estadoDeError adentro del objeto respuesta y si 
				//hubo error hay que enviar mail y ETC ETC ETC
			
			DateTime fin = DateTime.now();
			
			AgregarProcesoEjecutado(proceso, usuario, inicio, fin, respuesta);
			
			return respuesta;
		}
		else
		{
			throw new Exception("El proceso no esta permitido");
		}
	}

	private void AgregarProcesoEjecutado(Proceso proceso, Usuario usuario,
			DateTime inicio, DateTime fin, RespuestaProceso respuesta) {
		
		ProcesoEjecutado procesoEj =
				new ProcesoEjecutado(proceso, usuario, inicio, fin, respuesta);
		
		procesosEjecutados.add(procesoEj);
		
	}

}
