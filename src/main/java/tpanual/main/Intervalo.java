package tpanual.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.Interval;

@Entity
@Table (name = "INTERVALO")
public class Intervalo {

	@Id @GeneratedValue
	@Column(name = "ID")	
	private int id;
	private long fechaDesde;
	private long fechaHasta;
	
	public Interval getIntervalo (){
		return new Interval(fechaDesde, fechaHasta);
	}
	
	public void setIntervalo(Interval intervalo){
		fechaDesde = intervalo.getStartMillis();
		fechaHasta = intervalo.getEndMillis();
	}

	public long getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(long fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public long getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(long fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
