package tpanual.jsfcontrollers.pojos.busqueda;


import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import tpanual.usuario.Usuario;

public class BusquedaPojo {
	private DateTime fecha;
	private Usuario usuario;
	private String[] parametros;
	private int[] ids;
	private int total;
	
	
	public DateTime getFecha() {
		return fecha;
	}
	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String[] getParametros() {
		return parametros;
	}
	public void setParametros(String[] parametros) {
		this.parametros = parametros;
	}
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
