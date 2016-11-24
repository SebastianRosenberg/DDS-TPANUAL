package tpanual.main.direccion;

import javax.persistence.*;

@Entity
@Table(name="PAIS")
public class Pais {
	@Id @GeneratedValue
	@Column(name = "ID")		
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	
	public Pais(String nombre){
		this.nombre=nombre;
	}
	
	//DEFAULT CONSTRUCTOR PARA HIBERNATE
	public Pais(){
		
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
	
	public String toString(){
		return nombre;
	}
	
}
