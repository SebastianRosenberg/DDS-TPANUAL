package tpanual.jsfBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tpanual.jsfcontrollers.BusquedaDePoisController;

@ManagedBean
public class AccionesBean {
	private Map<String, String> acciones;
	private List<String> accionesSeleccionadas = new ArrayList<String>();
	private String accion;

	@PostConstruct
	public void init() {

		// acciones
		acciones = new HashMap<String, String>();
		acciones.put("Totalizar por Fecha", "Totalizar por Fecha");
		acciones.put("Totalizar por Usuario", "Totalizar por Usuario");
	}

	public Map<String, String> getAcciones() {
		return acciones;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public List<String> getAccionesSeleccionadas() {
		return accionesSeleccionadas;
	}

	public void agregarAccion() {
		
		if (BusquedaDePoisController.estasLogueado()) {
			if (accion != "" && !accionesSeleccionadas.contains(accion))
				accionesSeleccionadas.add(accion);
		} else {
			this.redirectToPage("/login.xhtml");
		}
	}

	public void eliminarAccion(String accion) {
		if (accionesSeleccionadas.contains(accion))
			accionesSeleccionadas.remove(accion);
	}

	private void redirectToPage(String toUrl) {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();

			ExternalContext extContext = ctx.getExternalContext();
			String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, toUrl));

			extContext.redirect(url);
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}
