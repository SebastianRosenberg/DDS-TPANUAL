package tpanual.jsfBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;
import tpanual.utilitarios.Utilitarios;

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
		//this.esAdministrador = true;
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

	public void login() {
		String msg = "";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (usuario.equals("grupo4") && contrasenia.equals("grupo4")) {
			msg = "Usuario " + usuario + " autorizado";
		} else {
			msg = "Usuario no autorizado";
			severity = FacesMessage.SEVERITY_ERROR;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
	}

	public void cancelar() {
		setUsuario(null);
		setContrasenia(null);
	}

	public String loginNew() {
		String msg = "";
		//HibernateFactorySessions hs = new HibernateFactorySessions();
		Usuario usuarioBd;
		Severity severity = FacesMessage.SEVERITY_INFO;
		String resultado = new String();
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		//usuarioBd = hs.obtenerUsuario(1);
		//usuarioBd = Utilitarios.getHibernateFactorySessions().obtenerUsuario(3);
		//usuarioBd = Utilitarios.getHibernateFactorySessions().obtenerUsuarioPorNombre(usuario);
		
		//4.5.3
		usuarioBd = gestor.buscarUsuarioPorNombre(usuario);
		if (usuarioBd != null){
			this.setEsAdministrador(usuarioBd.isAdministrador());
			if (usuario.equals(usuarioBd.getNombre()) && usuarioBd.login(contrasenia)) {
				msg = "Usuario " + usuario + " autorizado";
				resultado = "Logueado";
			} else {
				msg = "Usuario no autorizado";
				resultado = "No Logueado";
				severity = FacesMessage.SEVERITY_ERROR;
		
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
		}else{
			msg = "Usuario inexistente";
			resultado = "No Logueado";
			severity = FacesMessage.SEVERITY_ERROR;
		}
		return resultado;
	}

	//public Boolean esAdminastrador() {
		//return true;

	//}

}
