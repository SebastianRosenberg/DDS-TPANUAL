package tpanual.jsfcontrollers.pojos.poi;

import java.util.List;

import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;

public class SucursalBancoPojo extends PoiPojo{
	private Direccion direccion;
	private List<Servicio> servicios;
	
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
}
