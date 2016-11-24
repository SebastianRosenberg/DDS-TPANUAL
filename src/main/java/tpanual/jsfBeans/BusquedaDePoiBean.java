package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

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
		
		setResultadoBusqueda(busquedaDePoisController.buscarPois(palabrasFiltros));
		
		/*SucursalBancoPojo aux = new SucursalBancoPojo();
		Direccion dir = new Direccion();
		dir.setCallePrincipal("Carolina Muzilli");
		dir.setNumero("5491");
		aux.setDireccion(dir);
		resultadoBusqueda.add(aux);*/
	 
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

}
