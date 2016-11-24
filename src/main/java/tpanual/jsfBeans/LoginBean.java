package tpanual.jsfBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import tpanual.jsfcontrollers.BusquedaDePoisController;
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
			BusquedaDePoisController.usuario_str=usuario;
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
	
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        BusquedaDePoisController.usuario_str = null;
        return "/login.xhtml?faces-redirect=true";
    }
    
    public String home() {
        return "/home.xhtml?faces-redirect=true";
    }

}
