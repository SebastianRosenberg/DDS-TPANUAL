package tpanual.main.poi;

import tpanual.jsfcontrollers.pojos.poi.ConvertibleAPoiPojo;
import tpanual.main.Dias;

public abstract class TipoPuntoInteres implements ConvertibleAPoiPojo{
	String horario;

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public abstract boolean estaDisponible(Dias dia, int hora, String x);
	abstract public int getRadioCercania();
	public abstract boolean coincidencia(String x);
	public abstract boolean cercanoEntre(double latitudPunto,double longitudPunto,double latitudCoordenada,double longitudCoordenada, int comunaId);
	
	public boolean equals(Object o){
		if (!(o instanceof TipoPuntoInteres))
			return false;
		TipoPuntoInteres tpi=(TipoPuntoInteres) o;
		return (tpi.horario!= null && tpi.horario.equals(this.horario));
	}
}