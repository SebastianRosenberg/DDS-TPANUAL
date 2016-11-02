package tpanual.main.poi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "PALABRA_CLAVE")
public class PalabraClave {
	
	
	public PalabraClave(String s){
		this.nombre = s;
	}
	
	public PalabraClave(){
		
	}
	
	@Id @Column(name = "ID")
	@GeneratedValue
	private int id;
	@Column (name = "NOMBRE")
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	public static List<PalabraClave> getListaPalabrasClave(List<String> lista){
		if (lista!=null){
			List<PalabraClave> salida = new ArrayList<PalabraClave>();
			Iterator<String> it = lista.iterator();
			while (it.hasNext()){
				String s = it.next();
				PalabraClave p = new PalabraClave(s);
				salida.add(p);
			}
			return salida;
		}else{
			return null;
		}
	}
}
