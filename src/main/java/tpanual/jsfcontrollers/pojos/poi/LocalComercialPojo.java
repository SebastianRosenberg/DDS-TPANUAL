package tpanual.jsfcontrollers.pojos.poi;

import tpanual.main.direccion.Direccion;

public class LocalComercialPojo extends PoiPojo {
	private Direccion direccion;
	private String nombre;
	private String rubro;
	
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
}
