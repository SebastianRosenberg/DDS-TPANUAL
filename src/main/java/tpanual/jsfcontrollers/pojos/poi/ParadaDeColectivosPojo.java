package tpanual.jsfcontrollers.pojos.poi;

public class ParadaDeColectivosPojo extends PoiPojo {
	private String numeroLinea;
	private String direccion = "";
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
	public String getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
		this.valorAMostrarPorNombre = "Linea " + this.numeroLinea;
	}

	public String getDireccion() {
		return direccion;
		
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
		this.valorAMostrarPorDireccion = this.direccion.toString();
	}

}
