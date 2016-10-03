package tpanual.reportes;

import tpanual.usuario.Usuario;

public class CantidadPorUsuario implements CriterioReporte
{	
	public int cantidad;
	public Usuario usuario;
	
	public String toString(){
		String user;
		if (usuario==null)
			user="Usuario nulo";
		else
			user=usuario.toString();
		return Integer.valueOf(cantidad) + ": " + user;
	}
}