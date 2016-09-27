package tpanual.reportes;

import administrador.Busqueda;

public class CantidadPorBusquedaPorUsuario implements CriterioReporte{
	public int cantidad;
	public Busqueda busqueda;
	
	public String toString(){
		return Integer.valueOf(cantidad) + ": " + busqueda.toString();
	}
	
}