package tpanual.main.direccion;

public class Provincia {
	private int id;
	private String nombre;
	private Pais pais;
	
	public Provincia(String nombre, Pais pais){
		this.nombre=nombre;
		this.pais=pais;
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public String toString(){
		return nombre;
	}
	
}
