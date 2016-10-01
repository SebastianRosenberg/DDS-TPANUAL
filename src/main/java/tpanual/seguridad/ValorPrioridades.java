package tpanual.seguridad;

import tpanual.usuario.Usuario;

public class ValorPrioridades {

	private Usuario user;
	private boolean prioridad1;
	private boolean prioridad2;
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public boolean getPrioridad1() {
		return prioridad1;
	}
	
	public void setPrioridad1(boolean prioridad1) {
		this.prioridad1 = prioridad1;
	}
	
	public boolean getPrioridad2() {
		return prioridad2;
	}
	
	public void setPrioridad2(boolean prioridad2) {
		this.prioridad2 = prioridad2;
	}
	
}
