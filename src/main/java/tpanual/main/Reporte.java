package tpanual.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.joda.time.DateTime;

import tpanual.main.Reporte.CantidadPorUsuario;
import tpanual.usuario.Usuario;
import administrador.AdministradorDeBusquedas;
import administrador.Busqueda;

public class Reporte {
	
	AdministradorDeBusquedas adb = AdministradorDeBusquedas.getInstance();
	List<Busqueda> todasLasBusquedas;
	
	/**
	 * Constructor de Reporte que obtiene todas las busquedas existentes al momento de ejecutarse.
	 */
	public Reporte(){
		todasLasBusquedas = adb.getBusquedas();
	}
	
	/**
	 * Genera un informe con la cantidad de busquedas realizadas en cada fecha.
	 * ATENCION: No se muestran las fechas con 0 busquedas.
	 * @return Lista de objetos CantidadPorFecha (1 fila por fecha)
	 */
	public List<CantidadPorFecha> GenerarReporteCantidadPorFecha(){
		
		List<CantidadPorFecha> listReporte = new ArrayList<CantidadPorFecha>();
		
		for(Busqueda unaBusqueda : todasLasBusquedas){
			DateTime fecha = unaBusqueda.getFechaDeBusqueda();
			
			Supplier<Stream<CantidadPorFecha>> streamSupplier = () -> listReporte.stream().filter(b -> b.fecha == fecha);
			CantidadPorFecha aux = null;
			if(streamSupplier.get().count() > 0){
				 aux = (CantidadPorFecha) streamSupplier.get().findFirst().get();
			}
		    if(aux != null){
		    	aux.cantidad++;
		    }
		    else{
		    	aux = new CantidadPorFecha();
		    	aux.cantidad = 1;
		    	aux.fecha = fecha;
		    	listReporte.add(aux);
		    }
		}
		
		return listReporte;
	}
	
	/**
	 * Genera un informe con la sumatoria de la cantidad de resultados que cada usuario tuvo en sus busquedas.
	 * @return Lista de objetos CantidadPorUsuario (1 fila por usuario)
	 */
	public List<CantidadPorUsuario> GenerarReporteCantidadPorUsuario(){
		
		List<CantidadPorUsuario> listReporte = new ArrayList<CantidadPorUsuario>();
		for(Busqueda unaBusqueda : todasLasBusquedas){
			Usuario usuario = unaBusqueda.getUsuario();
			
			

			Supplier<Stream<CantidadPorUsuario>> streamSupplier = () -> listReporte.stream().filter(b -> b.usuario == usuario);
			CantidadPorUsuario aux = null;
			if(streamSupplier.get().count() > 0){
				aux = (CantidadPorUsuario) streamSupplier.get().findFirst().get();
			}
			
		    if(aux != null){
		    	aux.cantidad += unaBusqueda.getIdsEncontrados().length;
		    }
		    else{
		    	aux = new CantidadPorUsuario();
		    	aux.cantidad = unaBusqueda.getIdsEncontrados().length;
		    	aux.usuario = usuario;
		    	listReporte.add(aux);
		    }
		}
		
		return listReporte;
	}
	
	/**
	 * Genera un informe con la cantidad de resultados de cada busqueda realizada por el usuario dado.
	 * @param
	 * @return Lista de objetos CantidadPorBusquedaPorUsuario (1 fila por busqueda)
	 */
	public List<CantidadPorBusquedaPorUsuario> GenerarReporteCantidadPorBusquedaPorUsuario(Usuario usuario){
		
		List<CantidadPorBusquedaPorUsuario> listReporte = new ArrayList<CantidadPorBusquedaPorUsuario>();
		for(Busqueda unaBusqueda : todasLasBusquedas){
			if (usuario == unaBusqueda.getUsuario())
			{
				CantidadPorBusquedaPorUsuario aux = new CantidadPorBusquedaPorUsuario();
				
				aux.cantidad = unaBusqueda.getIdsEncontrados().length;
				aux.busqueda = unaBusqueda;
				listReporte.add(aux);
			}
		}
		
		return listReporte;
	}
	
	
	public class CantidadPorFecha{
		public int cantidad;
		public DateTime fecha;
	}
	public class CantidadPorUsuario
	{	
		public int cantidad;
		public Usuario usuario;
	}
	public class CantidadPorBusquedaPorUsuario{
		public int cantidad;
		public Busqueda busqueda;
	}
}   

