package tpanual.jsfcontrollers.pojos.poi;

import tpanual.main.direccion.Direccion;

	public class PoiPojo {
	private double latitud;
	private double longitud;
	private String nombre;
	private String direccion;
	private String infoExtra;
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public String getInfoExtra() {
		return infoExtra;
	}
	public void setInfoExtra(String infoExtra) {
		this.infoExtra = infoExtra;
	}
	public boolean equals(Object obj){
		if (!(obj instanceof PoiPojo))
			return false;
		PoiPojo a = (PoiPojo) obj
		return this.nombre.equals(a.nombre);
	}
		

}
