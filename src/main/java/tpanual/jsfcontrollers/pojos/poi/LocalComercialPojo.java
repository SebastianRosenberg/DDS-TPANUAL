package tpanual.jsfcontrollers.pojos.poi;

import tpanual.main.direccion.Direccion;

public class LocalComercialPojo extends PoiPojo {
	private Direccion direccion;
	private String nombre;
	private String rubro;
	private String valorAMostrarPorNombre;
	private String valorAMostrarPorDireccion;
	public String getValorAMostrarPorDireccion() {
		return valorAMostrarPorDireccion;
	}
	public void setValorAMostrarPorDireccion(String valorAMostrarPorDireccion) {
		this.valorAMostrarPorDireccion = valorAMostrarPorDireccion;
	}
	public String getValorAMostrarPorNombre() {
		return valorAMostrarPorNombre;
	}
	public void setValorAMostrarPorNombre(String valorAMostrarPorNombre) {
		this.valorAMostrarPorNombre = valorAMostrarPorNombre;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
		this.valorAMostrarPorDireccion = this.direccion.toString();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.valorAMostrarPorNombre = this.nombre;
	}
	public String getRubro() {
		return rubro;
	}
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
}
