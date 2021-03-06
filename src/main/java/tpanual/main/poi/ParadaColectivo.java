package tpanual.main.poi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import administrador.Mapa;
import tpanual.jsfcontrollers.pojos.poi.ParadaDeColectivosPojo;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.utilitarios.Constantes;

@Entity
@Table (name = "POI_PARADA_DE_COLECTIVO")
public class ParadaColectivo extends TipoPuntoInteres {
	
	@Column (name = "LINEA")
	private String linea;

	public String getLinea() {
		return linea;
	}

	public ParadaColectivo(String linea) {
		this.linea = linea;
	}
	
	public ParadaColectivo() {
	
	}

	public boolean estaDisponible (int dia, int hora, String x) {
		//super.setHorario("09:00 a 18:00");
		return Constantes.PARADA_DE_COLECTIVO_DISPONIBILIDAD; //Una parada de colectivos siempre está dispnible
	}
	
	public int getRadioCercania(){
		return Constantes.PARADA_DE_COLECTIVO_RADIO_DE_CERCANIA;
	}

	@Override
	public boolean coincidencia(String x) {
		return linea.toUpperCase().indexOf(x.toUpperCase()) != -1;
	}

	@Override
	public boolean cercanoEntre(double latitudPunto, double longitudPunto, double latitudCoordenada,
			double longitudCoordenada, int comunaId) {
		Mapa map1 = Mapa.getInstance();
		return (this.getRadioCercania()) >= (int) (PuntoDeInteres.distance(latitudPunto,longitudPunto,latitudCoordenada,longitudCoordenada,"K") * 1000);
		
	}
	
	public boolean equals(Object o){
		if (!(o instanceof LocalComercial))
			return false;
		ParadaColectivo pc=(ParadaColectivo) o;
		return ((linea!=null && linea.equals(pc.linea) || linea==null && pc.linea==null) && super.equals(o));
	}

	@Override
	public PoiPojo convertir(PuntoDeInteres p) {
		ParadaDeColectivosPojo col = new ParadaDeColectivosPojo();
		col.setNumeroLinea(linea);
		col.setDireccion(p.getDireccion().toString());
		col.setLatitud(p.getLatitud());
		col.setLongitud(p.getLongitud());
		col.setNombre("Linea "+ linea);
		col.setInfoExtra("");
		return col;
	}	
}
