package tpanual.jsfcontrollers.pojos.poi;

import java.util.List;

import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;

public class CgpPojo extends PoiPojo{
	private Direccion direccion;
	private List<Servicio> servicios;
	private int idComuna;
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
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public int getIdComuna() {
		return idComuna;
	}
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
		this.valorAMostrarPorNombre = "CGP " + this.idComuna;
	}
}
