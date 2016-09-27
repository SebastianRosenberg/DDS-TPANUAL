package tpanual.reportes;

import org.joda.time.DateTime;

public class CantidadPorFecha implements CriterioReporte{
	public int cantidad;
	public DateTime fecha;
	
	public String toString(){
		return Integer.valueOf(cantidad) + ": " + fecha.toString();
	}
}