package tpanual.main.poi;

import tpanual.jsfcontrollers.pojos.poi.ConvertibleAPoiPojo;
import tpanual.main.Dias;

public abstract class TipoPuntoInteres implements ConvertibleAPoiPojo{

	
	public abstract boolean estaDisponible(Dias dia, int hora, String x);
	abstract public int getRadioCercania();
	public abstract boolean coincidencia(String x);
	public abstract boolean cercanoEntre(double latitudPunto,double longitudPunto,double latitudCoordenada,double longitudCoordenada, int comunaId);
	
	public boolean equals(Object o){
		if (!(o instanceof TipoPuntoInteres))
			return false;
		return true;
	}
}