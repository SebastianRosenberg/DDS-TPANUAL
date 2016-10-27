package tpanual.main.direccion;

public class Localidad {
	private int id;
	private String nombre;
	private int codigoPostal;
	private Provincia provincia;
	
	public Localidad(String nombre, int codigoPostal, Provincia provincia){
		this.nombre=nombre;
		this.codigoPostal=codigoPostal;
		this.provincia=provincia;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public static Localidad getLocalidadAuxiliar(){
		Pais pais = new Pais("Argentina");
		Provincia provincia = new Provincia("Buenos Aires", pais);
		Localidad localidad = new Localidad("Castelar", 1712, provincia);
		return localidad;
	}
	
	public String toString(){
		return nombre;
	}
	
}
