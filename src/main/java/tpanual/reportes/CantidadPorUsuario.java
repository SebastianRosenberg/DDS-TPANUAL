package tpanual.reportes;

import tpanual.usuario.Usuario;

public class CantidadPorUsuario implements CriterioReporte
{	
	public int cantidad;
	public Usuario usuario;
	
	public String toString(){
		return Integer.valueOf(cantidad) + ": " + usuario.toString();
	}
}