package tpanual.main.poi;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;

import tpanual.jsfcontrollers.pojos.poi.ConvertibleAPoiPojo;
import tpanual.main.Dias;

@Entity
public abstract class TipoPuntoInteres implements ConvertibleAPoiPojo{

	@Id @Column (name = "ID")
	@GeneratedValue
	private int id;
	
	public abstract boolean estaDisponible(Dias dia, int hora, String x);
	abstract public int getRadioCercania();
	public abstract boolean coincidencia(String x);
	public abstract boolean cercanoEntre(double latitudPunto,double longitudPunto,double latitudCoordenada,double longitudCoordenada, int comunaId);
	
	public boolean equals(Object o){
		if (!(o instanceof TipoPuntoInteres))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}