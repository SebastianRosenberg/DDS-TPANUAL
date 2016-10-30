package tpanual.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationFieldType;
import org.joda.time.Interval;
import org.joda.time.LocalTime;

@Entity
@Table (name = "SERVICIO")
public class Servicio {
	
	@Id @GeneratedValue
	@Column (name = "ID")
	private int id;
	@Column (name = "NOMBRE")
	private String nombre;
	@Transient
	private HorarioDeAtencion horario;
	
	public Servicio(String nombre){
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public HorarioDeAtencion getHorario(){
		return horario;
	}
	
	public void setHorario(HorarioDeAtencion horario){
		this.horario = horario;
	}
	
	/**
	 * Recibe una cantidad indeterminada de String y crea una clase Servicio con cada uno, luego devuelve la lista que las contiene a todas	
	 * @param s
	 * @return
	 */
	public static List<Servicio> getListaServicios(String... s){
		List<Servicio> l=new ArrayList<Servicio>();
		for (int a=0;a<s.length;a++){
			l.add(new Servicio(s[a]));
		}
		return l;
	}

	public int getId() {
		return id;
	}

}

