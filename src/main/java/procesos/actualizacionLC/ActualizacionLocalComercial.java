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
	
	@Override
	public boolean equals (Object obj){

		if((((ActualizacionLocalComercial) (obj)).getPoi().equals(poi)) && (((ActualizacionLocalComercial) (obj)).getPalabrasNuevas().equals(palabrasNuevas))){
			return true;
		}
		else{
			return false;
		}
	}
	
}
