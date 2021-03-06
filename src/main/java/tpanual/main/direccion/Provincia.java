package tpanual.main.direccion;

import javax.persistence.*;

@Entity
@Table ( name = "PROVINCIA")
public class Provincia {
	@Id @GeneratedValue
	@Column(name = "ID")	
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "PAIS_ID")
	private Pais pais;
	
	public Provincia(String nombre, Pais pais){
		this.nombre=nombre;
		this.pais=pais;
	}
	
	//DEFAULT CONSTRUCTOR PARA HIBERNATE
	public Provincia(){
		
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
