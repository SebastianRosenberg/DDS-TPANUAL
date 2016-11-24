package tpanual.rubro;

import javax.persistence.*;

import tpanual.main.HorarioDeAtencion;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class RubroFW {
	
	@Id @Column (name = "ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected int id;
	
	public abstract int getCercania();
	public abstract String getNombre();
	public abstract boolean esIgual(String nombre, int cercania);
}
