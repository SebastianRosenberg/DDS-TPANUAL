package tpanual.jsfBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

@ManagedBean
public class LoginBean {
	private String usuario;
	private String contrasenia;

	public String getUsuario () {
		return usuario;
	}

	public void setUsuario (final String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia () {
		return contrasenia;
	}

	public void setContrasenia	 (final String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public void login() {
		 String msg = "";
		 Severity severity = FacesMessage.SEVERITY_INFO;
		 if (usuario.equals("grupo4") && contrasenia.equals("grupo4")) {
			 msg = "Usuario " + usuario + " autorizado";
		 } else {
			 	msg = "Usuario no autorizado";
			 	severity = FacesMessage.SEVERITY_ERROR;
		 }
		 FacesContext.getCurrentInstance().addMessage(null,
				 new FacesMessage(severity, msg, null));
		 }
		 
	public void cancelar(){
		 	setUsuario(null);
		 	setContrasenia(null);
		 }
	
	public void loginNew() {
		 String msg = "";
		 HibernateFactorySessions hs = new HibernateFactorySessions();
		 Usuario usuarioBd;
		 Severity severity = FacesMessage.SEVERITY_INFO;
		 
		 usuarioBd = hs.obtenerUsuario(1);
		 if (usuario.equals(usuarioBd.getNombre()) && contrasenia.equals("grupo4")) {
			 msg = "Usuario " + usuario + " autorizado";
		 } else {
			 	msg = "Usuario no autorizado";
			 	severity = FacesMessage.SEVERITY_ERROR;
		 }
		 FacesContext.getCurrentInstance().addMessage(null,
				 new FacesMessage(severity, msg, null));
		 }

}
