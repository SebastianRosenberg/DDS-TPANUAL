package tpanual.main.poi;

import tpanual.main.direccion.Direccion;

public class PoiInfoBasica {
	
	private Integer id;
	private String nombre;
	private Direccion direccion;
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
