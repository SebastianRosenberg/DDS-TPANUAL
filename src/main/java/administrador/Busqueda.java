package administrador;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class Busqueda {
	private DateTime fechaDeBusqueda;
	private String[] stringsBuscados;
	private int[] idsEncontrados;
	private Usuario usuario;
	private Duration duracion;
	
	public Busqueda(String[] stringsBuscados, int[] idsEncontrados, Usuario usuario, Duration duracion, DateTime dateTime){
		this.stringsBuscados=stringsBuscados;
		this.idsEncontrados=idsEncontrados;
		this.fechaDeBusqueda=dateTime;
		this.usuario=usuario;
		this.duracion=duracion;
	}
	
	public DateTime getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}
	public String[] getStringsBuscados() {
		return stringsBuscados;
	}
	public int[] getIdsEncontrados() {
		return idsEncontrados;
	}
	
	public boolean coincideBusqueda(String[] x){
		if (x.length==0 || x.length!=stringsBuscados.length) return false;
		boolean coincidencia=true;
		for (int i=0;i<x.length;i++){
			coincidencia=coincidencia && (x[i]==null && stringsBuscados[i]==null || (x[i]!=null && (x[i].indexOf(stringsBuscados[i]))!=1));
		}
		return coincidencia && Busqueda.fechaValida(fechaDeBusqueda);
	}

	public static boolean fechaValida(DateTime fecha){
		DateTime horaActual=new DateTime();
		Duration duracion=new Duration(fecha, horaActual);
		return duracion.getStandardHours()<Constantes.INTERVALO_DEHORAS_CONSIDERA_BUSQUEDA_RECIENTE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Duration getDuracion() {
		return duracion;
	}	
}
