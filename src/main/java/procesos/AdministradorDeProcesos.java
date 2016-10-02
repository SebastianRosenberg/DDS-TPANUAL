package procesos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Email;

public class AdministradorDeProcesos {
	
	public enum EstadoResultado{
		OK,
		ERROR
		}
	
     static class ProcesoEjecutado{
		
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
	
	static List<ProcesoEjecutado> procesosEjecutados = new ArrayList<ProcesoEjecutado>();
	static List<Proceso> procesosDisponibles = new ArrayList<Proceso>();
	
	
	static public void AgregarProcesoDisponible(Proceso proceso){
		procesosDisponibles.add(proceso);
	}
	
	static public void QuitarProcesoDisponible(Proceso proceso){
		procesosDisponibles.remove(proceso);
	}
	
	static public List<Proceso> GetProcesosDisponiblesParaElUsuario(Usuario usuario){
		//GestorDeUsuarios gestorUsuarios = GestorDeUsuarios.getInstance();
		
		//TODO: preguntar si es admin
		//if (ES ADMINISTRADOR)
		return procesosDisponibles;
		
		//else
		//return new List<Proceso>();
	}
	
	static public List<ProcesoEjecutado> GetProcesosEjecutados(){
		return procesosEjecutados;
	}
	
	static public RespuestaProceso EjecutarProceso(Proceso proceso, Usuario usuario, AccionEnCasoDeError accionError )throws Exception{
		RespuestaProceso respuesta;
		if(procesosDisponibles.contains(proceso)){//Esto va a haber que cambiarlo para ver si es permitido para el usuario
			
			DateTime inicio = DateTime.now();
			
				respuesta = proceso.procesar();
			
				if(respuesta.getEstado() == EstadoResultado.ERROR){
					respuesta = accionError.RealizarAccion(usuario, proceso, respuesta);
				}
			
			DateTime fin = DateTime.now();
			
			AgregarProcesoEjecutado(proceso, usuario, inicio, fin, respuesta);
			
			return respuesta;
		}
		else
		{
			throw new Exception("El proceso no esta permitido");
		}
	}

	 public static void AgregarProcesoEjecutado(Proceso proceso, Usuario usuario,
			DateTime inicio, DateTime fin, RespuestaProceso respuesta) {
		
		ProcesoEjecutado procesoEj =
				new ProcesoEjecutado(proceso, usuario, inicio, fin, respuesta);
		
		procesosEjecutados.add(procesoEj);
		
	}

}

