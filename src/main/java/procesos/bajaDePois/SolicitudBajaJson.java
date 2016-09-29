package procesos.bajaDePois;

public class SolicitudBajaJson {
	private String busqueda;
	private Long fecha;
	
	public SolicitudBajaJson(String b, Long f){
		this.busqueda=b;
		this.fecha=f;
	}
	
	public String getBusqueda() {
		return busqueda;
	}
	public Long getFecha() {
		return fecha;
	}
}
