package tpanual.reportes;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	
	
	public static List<Busqueda> getBusquedasPorUsuario(Usuario user){
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
		List<Busqueda> listaFinal = new ArrayList<Busqueda>();
		
		Iterator<Busqueda> it = todasLasBusquedas.iterator();
		while (it.hasNext()){
			Busqueda b = it.next();
			if (b.getUsuario() != null && b.getUsuario().equals(user))
				listaFinal.add(b);
		}
		return listaFinal;
	}
	
	public static List<Busqueda> getBusquedasPorFecha(DateTime fechaDesde, DateTime fechaHasta){
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
		List<Busqueda> listaFinal = new ArrayList<Busqueda>();
		
		Iterator<Busqueda> it = todasLasBusquedas.iterator();
		while (it.hasNext()){
			Busqueda b = it.next();
			DateTime fechaBusqueda = b.getFechaDeBusquedaJoda();
			if (fechaDesde!=null && fechaDesde.isBefore(fechaBusqueda) || fechaDesde == null){
				if (fechaHasta!=null && fechaHasta.isAfter(fechaBusqueda) || fechaHasta == null){
					listaFinal.add(b);
				}
				
			}
				
		}
		return listaFinal;
	}	
	
	
	/**
	 * Genera un informe con la cantidad de busquedas realizadas en cada fecha.
	 * ATENCION: No se muestran las fechas con 0 busquedas.
	 * @return Lista de objetos CantidadPorFecha (1 fila por fecha)
	 */
	public static Reporte<CantidadPorFecha> GenerarReporteCantidadPorFecha(){
		
		List<Busqueda> todasLasBusquedas=AdministradorDeBusquedas.getInstance().getBusquedas();
		List<CantidadPorFecha> listReporte = new ArrayList<CantidadPorFecha>();
		
		for(Busqueda unaBusqueda : todasLasBusquedas){
			DateTime fecha = unaBusqueda.getFechaDeBusquedaJoda();
			
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
			
			if (usuario == null || unaBusqueda.getPoiEncontrados() == null || 
					unaBusqueda.getPoiEncontrados().isEmpty() || unaBusqueda.getStringsBuscados() == null
					|| unaBusqueda.getStringsBuscados().length == 0)
				continue;

			Supplier<Stream<CantidadPorUsuario>> streamSupplier = () -> listReporte.stream().filter(b -> b.usuario == usuario);
			CantidadPorUsuario aux = null;
			if(streamSupplier.get().count() > 0){
				aux = (CantidadPorUsuario) streamSupplier.get().findFirst().get();
			}
			
		    if(aux != null){
		    	aux.cantidad += unaBusqueda.getPoiEncontrados().size();
		    }
		    else{
		    	aux = new CantidadPorUsuario();
		    	aux.cantidad = unaBusqueda.getPoiEncontrados().size();
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
			if (usuario != null &&  usuario.equals(unaBusqueda.getUsuario()))
			{
				CantidadPorBusquedaPorUsuario aux = new CantidadPorBusquedaPorUsuario();
				
				aux.cantidad = unaBusqueda.getPoiEncontrados().size();
				aux.busqueda = unaBusqueda;
				listReporte.add(aux);
			}
		}
		
		return new Reporte<CantidadPorBusquedaPorUsuario>(listReporte, MostrablePorConsola.getInstance());
	}
	

}   

