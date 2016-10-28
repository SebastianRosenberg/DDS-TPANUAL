package tpanual.Rubro;

import javax.persistence.*;

@Entity
@Table (name = "Rubro")
public class RubroConcreteFW extends RubroFW{

	@Column (name = "NOMBRE")
	private String nombre;
	@Column (name = "CERCANIA")
	private int cercania;
	
	public RubroConcreteFW(String nombre, int cercania){
		this.nombre=nombre;
		this.cercania=cercania;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getCercania() {
		return cercania;
	}
	
	public boolean esIgual(String nombre, int cercania){
		return (cercania==this.cercania && 
				(nombre!=null && nombre.equals(this.nombre) || nombre==null));  		
	}
	
	public boolean equals(Object o){
		if (!(o instanceof RubroConcreteFW))
			return false;
		RubroConcreteFW rc=(RubroConcreteFW) o;
		return rc.esIgual(nombre, cercania);
	}

	
}
