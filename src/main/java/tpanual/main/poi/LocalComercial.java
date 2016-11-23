package tpanual.main.poi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.joda.time.Interval;

import administrador.Mapa;
import tpanual.Rubro.RubroFW;
import tpanual.jsfcontrollers.pojos.poi.LocalComercialPojo;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.HorarioDeAtencion;

@Entity
@Table (name = "POI_LOCAL_COMERCIAL")
public class LocalComercial extends TipoPuntoInteres {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "RUBRO_ID")
	private RubroFW rubro;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "HORARIO_DE_ATENCION_ID" )
	private HorarioDeAtencion horario;
	
	public LocalComercial(RubroFW rubro, HorarioDeAtencion hda){
		this.rubro=rubro;
		horario=hda;
	}
	
	public LocalComercial() {
	
	}
	
	@Override
	public boolean estaDisponible(int dia, int hora, String x) {
		return horario.estaEnHorarioDeAtencion(dia, hora);
	}

	public int getRadioCercania() {
		return rubro.getCercania();
	}

	@Override
	public boolean coincidencia(String x) {	
		
		return rubro.getNombre().toUpperCase().indexOf(x.toUpperCase())!=-1;
	}

	@Override
	public boolean cercanoEntre(double latitudPunto, double longitudPunto, double latitudCoordenada,
			double longitudCoordenada, int comunaId) {

		return (this.getRadioCercania()) >= (int) (PuntoDeInteres.distance(latitudPunto,longitudPunto,latitudCoordenada,longitudCoordenada,"K") * 1000);
	}
	public boolean equals(Object o){
		if (!(o instanceof LocalComercial))
			return false;
		LocalComercial lc=(LocalComercial) o;
		return ((rubro!=null && rubro.equals(lc.rubro) || rubro==null && lc.rubro==null) && 
				(horario!=null && horario.equals(lc.horario) || horario==null && lc.horario==null) && super.equals(o));
	}

	@Override
	public PoiPojo convertir(PuntoDeInteres p) {
		LocalComercialPojo l = new LocalComercialPojo();
		l.setDireccion(p.getDireccion().toString());
		l.setNombre(p.getNombre());
		l.setLatitud(p.getLatitud());
		l.setLongitud(p.getLongitud());
		l.setRubro(rubro.getNombre());
		l.setInfoExtra("Rubro:<br/> "+ rubro.getNombre() + ".<br/><br/> Horarios de atención:<br/> " + horario.toString());
		return l;
		
	}
}
