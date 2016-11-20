package tpanual.jsfBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import tpanual.jsfcontrollers.UsuariosController;

@ManagedBean
public class LoginBean {
	private String usuario;
	private String contrasenia;
	private boolean esAdministrador;

	public boolean getEsAdministrador() {
		return esAdministrador;
	}

	public void setEsAdministrador(boolean esAdministrador) {
		this.esAdministrador = esAdministrador;
		// this.esAdministrador = true;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(final String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void cancelar() {
		setUsuario(null);
		setContrasenia(null);
	}

	public String loginNew() {
		String msg = "";
		Severity severity = FacesMessage.SEVERITY_INFO;
		// String resultado = new String();
		UsuariosController userC = new UsuariosController();

		boolean usuarioC = userC.buscarUsuario(usuario, contrasenia);
		
		if (usuarioC) {
			esAdministrador = userC.esAdministrador();;
			msg = "Usuario " + usuario + " autorizado";
			this.mensajeAPantalla(msg, severity);
			return "Logueado";
		} else {
			msg = "Usuario no autorizado";
			severity = FacesMessage.SEVERITY_ERROR;
			this.mensajeAPantalla(msg, severity);
			return "No Logueado";
		}
	}

	private void mensajeAPantalla(String mensaje, Severity severidad) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, mensaje, null));

	}

}
