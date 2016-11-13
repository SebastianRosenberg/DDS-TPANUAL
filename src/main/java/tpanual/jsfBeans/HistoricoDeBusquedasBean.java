package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;

import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.usuario.Usuario;
import tpanual.jsfcontrollers.HistorialDeBusquedasController;

@ManagedBean
public class HistoricoDeBusquedasBean {
	
	private String nombreUsuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private List<BusquedaPojo> busquedas = new ArrayList<BusquedaPojo>();
	private HistorialDeBusquedasController busquedasController= new HistorialDeBusquedasController();
	
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
	
	public void buscar(){
		DateTime fechaDesdeJoda = new DateTime(fechaDesde);
		DateTime fechaHastaJoda = new DateTime(fechaHasta);
	//	Usuario usuario = new Usuario(null);//obtener un usuario a partir de su nombre?
		System.out.println(fechaDesde);
		System.out.println(fechaDesde);
		//busquedas = busquedasController.getHistorialBusquedas(fechaDesdeJoda, fechaHastaJoda, nombreUsuario); 
	}
	
}