package tpanual.main;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import tpanual.usuario.Usuario;
import administrador.AdministradorDeBusquedas;
import administrador.Busqueda;

public class Reporte {
	
	AdministradorDeBusquedas adb = AdministradorDeBusquedas.getInstance();
	List<Busqueda> todasLasBusquedas = adb.getBusquedas();
	
	public List<CantidadPorFecha> GenerarReporteCantidadPorFecha(){
		
		List<CantidadPorFecha> listReporte = new ArrayList<CantidadPorFecha>();
		for(Busqueda unaBusqueda : todasLasBusquedas){
			DateTime fecha = unaBusqueda.getFechaDeBusqueda();
			
		    CantidadPorFecha aux = listReporte.stream().filter(b -> b.fecha == fecha).findFirst().get();
		    if(aux != null){
		    	aux.cantidad++;
		    }
		    else{
		    	aux.cantidad = 1;
		    	aux.fecha = fecha;
		    	listReporte.add(aux);
		    }
		}
		
		return listReporte;
	}
	public List<CantidadPorUsuario> GenerarReporteCantidadPorUsuario(){
		
		List<CantidadPorUsuario> listReporte = new ArrayList<CantidadPorUsuario>();
		for(Busqueda unaBusqueda : todasLasBusquedas){
			Usuario usuario = unaBusqueda.getUsuario();
			
			CantidadPorUsuario aux = listReporte.stream().filter(b -> b.usuario == usuario).findFirst().get();
		    if(aux != null){
		    	aux.cantidad++;
		    }
		    else{
		    	aux.cantidad = 1;
		    	aux.usuario = usuario;
		    	listReporte.add(aux);
		    }
		}
		
		return listReporte;
	}
	
	//Devuelve la cantidad de resultados por busqueda de un usuario
	public List<CantidadPorBusquedaPorUsuario> GenerarReporteCantidadPorBusquedaPorUsuario(Usuario usuario){
		
		List<Busqueda> busquedasDelUsuario = new ArrayList<Busqueda>();
		List<CantidadPorBusquedaPorUsuario> listReporte = new ArrayList<CantidadPorBusquedaPorUsuario>();
		for(Busqueda unaBusqueda : todasLasBusquedas){
			if (usuario == unaBusqueda.getUsuario())
			{
				busquedasDelUsuario.add(unaBusqueda);
			}
		}
	
		for (Busqueda unaBusqueda : busquedasDelUsuario)
		{
			CantidadPorBusquedaPorUsuario aux = new CantidadPorBusquedaPorUsuario();
			
			aux.cantidad = unaBusqueda.getIdsEncontrados().length;
			aux.busqueda = unaBusqueda;
			listReporte.add(aux);
		}
		
		return listReporte;
	}
	
	
	public class CantidadPorFecha{
		int cantidad;
		DateTime fecha;
	}
	public class CantidadPorUsuario
	{	
		int cantidad;
		Usuario usuario;
	}
	public class CantidadPorBusquedaPorUsuario{
		int cantidad;
		Busqueda busqueda;
	}
}   

