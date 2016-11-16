package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.joda.time.DateTime;
import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.jsfcontrollers.HistorialDeBusquedasController;

@ManagedBean
public class HistoricoDeBusquedasBean {

	private String nombreUsuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<BusquedaPojo> busquedas = new ArrayList<BusquedaPojo>();
	private List<String> infoPOI = new ArrayList<String>();
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
		DateTime fechaDesdeJoda = new DateTime(fechaDesde);
		DateTime fechaHastaJoda = new DateTime(fechaHasta);
		/*BusquedaPojo unaBusqueda = new BusquedaPojo();
		unaBusqueda.setFecha(new DateTime());
		unaBusqueda.setTotal(3);
		unaBusqueda.setParametros(new String[] { "uno", "dos" });
		unaBusqueda.setUsuario(GestorDeUsuarios.getInstance().crearTerminalActivo("pedro"));
		unaBusqueda.setIds(new int[] { 1, 2, 3 });
		busquedas.add(unaBusqueda);*/
		busquedas = busquedasController.getHistorialBusquedas(fechaDesdeJoda,fechaHastaJoda, nombreUsuario);
	}

	public void masInfo(int[] ids) {
		infoPOI.clear();
		for (int i = 0; i < ids.length; i++) {
			//infoPOI.add(AdministradorDePoi.getInstance().obtenerPoiPorId(i).getNombre());
			infoPOI.add(String.valueOf(i));
		}
	}

	public BusquedaPojo getBusquedaSeleccionada() {
		return busquedaSeleccionada;
	}

	public void setBusquedaSeleccionada(BusquedaPojo busquedaSeleccionada) {
		this.busquedaSeleccionada = busquedaSeleccionada;
	}

	public List<String> getInfoPOI() {
		return infoPOI;
	}

	public void setInfoPOI(List<String> infoPOI) {
		this.infoPOI = infoPOI;
	}

}