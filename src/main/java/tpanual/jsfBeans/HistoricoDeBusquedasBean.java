package tpanual.jsfBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;

import administrador.AdministradorDePoi;
import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.jsfcontrollers.BusquedaDePoisController;
import tpanual.jsfcontrollers.HistorialDeBusquedasController;

@ManagedBean
public class HistoricoDeBusquedasBean {

	private String nombreUsuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<BusquedaPojo> busquedas = new ArrayList<BusquedaPojo>();
	private List<PoiPojo> infoPOI = new ArrayList<PoiPojo>();
	private HistorialDeBusquedasController busquedasController = new HistorialDeBusquedasController();
	private BusquedaPojo busquedaSeleccionada = new BusquedaPojo();

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
	}

	public List<BusquedaPojo> getBusquedas() {
		return busquedas;
	}

	public void buscar() {

		if (BusquedaDePoisController.estasLogueado()) {
			DateTime fechaDesdeJoda = null;
			DateTime fechaHastaJoda = null;
			infoPOI.clear();
			if (fechaDesde != null)
				fechaDesdeJoda = new DateTime(fechaDesde);
			if (fechaHasta != null)
				fechaHastaJoda = new DateTime(fechaHasta);
			/*
			 * BusquedaPojo unaBusqueda = new BusquedaPojo();
			 * unaBusqueda.setFecha(new DateTime()); unaBusqueda.setTotal(3);
			 * unaBusqueda.setParametros(new String[] { "uno", "dos" });
			 * unaBusqueda.setUsuario(GestorDeUsuarios.getInstance().
			 * crearTerminalActivo("pedro")); unaBusqueda.setIds(new int[] { 1,
			 * 2, 3 }); busquedas.add(unaBusqueda);
			 */
			busquedas = busquedasController.getHistorialBusquedas(fechaDesdeJoda, fechaHastaJoda, nombreUsuario);
		} else {
			this.redirectToPage("/login.xhtml");
		}
	}

	public void masInfo(int[] ids) {
		infoPOI.clear();
		Iterator<BusquedaPojo> it1=busquedas.iterator();
		while (it1.hasNext()){
			BusquedaPojo bp = it1.next();
			List<PuntoDeInteres> l = bp.getPoisCompletos();
			Iterator<PuntoDeInteres> it2 = l.iterator();
			while (it2.hasNext()){
				PuntoDeInteres poi = it2.next();
				for (int i = 0; i < ids.length; i++) {
					if (ids[i] == poi.getId())
						infoPOI.add(poi.getPojo());
				}
			}
		}
	}

	public BusquedaPojo getBusquedaSeleccionada() {
		return busquedaSeleccionada;
	}

	public void setBusquedaSeleccionada(BusquedaPojo busquedaSeleccionada) {
		this.busquedaSeleccionada = busquedaSeleccionada;
	}

	public List<PoiPojo> getInfoPOI() {
		return infoPOI;
	}

	public void setInfoPOI(List<PoiPojo> infoPOI) {
		this.infoPOI = infoPOI;
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