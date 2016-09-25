package procesos.actualizacionLC;

import java.util.List;

import tpanual.main.poi.PuntoDeInteres;

public class ActualizacionLocalComercial {
	private PuntoDeInteres poi;
	private List<String> palabrasNuevas;
	
	public ActualizacionLocalComercial(PuntoDeInteres poi, List<String> palabrasNuevas){
		this.poi=poi;
		this.palabrasNuevas=palabrasNuevas;
	}
	
	
	public PuntoDeInteres getPoi() {
		return poi;
	}
	public List<String> getPalabrasNuevas() {
		return palabrasNuevas;
	}
	
	
	
}
