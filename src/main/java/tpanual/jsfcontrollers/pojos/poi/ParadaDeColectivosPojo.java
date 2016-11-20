package tpanual.jsfcontrollers.pojos.poi;

public class ParadaDeColectivosPojo extends PoiPojo {
	private String numeroLinea;
	private String direccion = "";
	private String nombre;
	public String getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

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

}
