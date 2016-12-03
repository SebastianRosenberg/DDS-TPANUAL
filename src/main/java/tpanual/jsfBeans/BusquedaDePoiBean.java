package tpanual.jsfBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import tpanual.jsfcontrollers.BusquedaDePoisController;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.jsfcontrollers.pojos.poi.SucursalBancoPojo;
import tpanual.main.direccion.Direccion;

@ManagedBean
public class BusquedaDePoiBean {
	BusquedaDePoisController busquedaDePoisController = new BusquedaDePoisController();
	private List<String> palabrasFiltros = new ArrayList<String>();
	private List<PoiPojo> resultadoBusqueda = new ArrayList<PoiPojo>();
	private String valorInputFiltros = "";
	private String usuario;

	public void agregarFiltro() {
		this.palabrasFiltros.add(valorInputFiltros);
	}

	public void limpiarFiltros() {
		this.palabrasFiltros = new ArrayList<String>();
	}

	public void buscar() {

		if (BusquedaDePoisController.estasLogueado()) {
			setResultadoBusqueda(busquedaDePoisController.buscarPois(palabrasFiltros));
		} else {
			this.redirectToPage("/login.xhtml");
		}

	}

	public List<String> getPalabrasFiltros() {
		return palabrasFiltros;
	}

	public void setPalabrasFiltros(List<String> palabrasFiltros) {
		this.palabrasFiltros = palabrasFiltros;
	}

	public String getValorInputFiltros() {
		return valorInputFiltros;
	}

	public void setValorInputFiltros(String valorInputFiltros) {
		this.valorInputFiltros = valorInputFiltros;
	}

	public List<PoiPojo> getResultadoBusqueda() {
		return resultadoBusqueda;
	}

	public void setResultadoBusqueda(List<PoiPojo> resultadoBusqueda) {
		this.resultadoBusqueda = resultadoBusqueda;
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
