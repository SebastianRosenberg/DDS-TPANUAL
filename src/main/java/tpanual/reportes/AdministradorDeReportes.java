package tpanual.reportes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.joda.time.DateTime;

import tpanual.main.gui.MetodoGrafico;
import tpanual.main.gui.Mostrable;
import tpanual.main.gui.MostrablePorConsola;
import tpanual.usuario.Usuario;
import administrador.AdministradorDeBusquedas;
import administrador.Busqueda;

public class AdministradorDeReportes  {
	
	
	/**
	 * Genera un informe con la cantidad de busquedas realizadas en cada fecha.
	 * ATENCION: No se muestran las fechas con 0 busquedas.
	 * @return Lista de objetos CantidadPorFecha (1 fila por fecha)
	 */
	public static Reporte<CantidadPorFecha> GenerarReporteCantidadPorFecha(){
		
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
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

		return new Reporte<CantidadPorFecha>(listReporte, MostrablePorConsola.getInstance());
	}
	
	/**
	 * Genera un informe con la sumatoria de la cantidad de resultados que cada usuario tuvo en sus busquedas.
	 * @return Lista de objetos CantidadPorUsuario (1 fila por usuario)
	 */
	public static Reporte<CantidadPorUsuario> GenerarReporteCantidadPorUsuario(){
		
		
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
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
		
		return new Reporte<CantidadPorUsuario>(listReporte, MostrablePorConsola.getInstance());
	}
	
	/**
	 * Genera un informe con la cantidad de resultados de cada busqueda realizada por el usuario dado.
	 * @param
	 * @return Lista de objetos CantidadPorBusquedaPorUsuario (1 fila por busqueda)
	 */
	public static Reporte<CantidadPorBusquedaPorUsuario> GenerarReporteCantidadPorBusquedaPorUsuario(Usuario usuario){
		
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
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
		
		return new Reporte<CantidadPorBusquedaPorUsuario>(listReporte, MostrablePorConsola.getInstance());
	}
	

}   

