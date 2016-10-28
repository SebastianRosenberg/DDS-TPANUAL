package tpanual.main.poi;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import administrador.Mapa;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.jsfcontrollers.pojos.poi.SucursalBancoPojo;
import tpanual.main.Dias;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.utilitarios.Constantes;

@Entity
@Table (name = "POI_SUCURSAL_BANCO")
public class SucursalBanco extends TipoPuntoInteres {
	@Transient
	List<Servicio> servicios;
	@Transient
	HorarioDeAtencion horario = new HorarioDeAtencion();

	public SucursalBanco(List<Servicio> lista) {
		servicios=lista;
		for (Dias dia : Dias.values()) { // Agrega el horario de atencion lunes a viernes de 10:00 a 15:00
			if (dia != Dias.DOMINGO && dia != Dias.SABADO)
				horario.addRangoDia(1000, 1500, dia);
		}
	}
	

	@Override
	public int getRadioCercania() {
		return Constantes.SUCURSAL_BANCO_RADIO_DE_CERCANIA;
	}

	@Override
	public boolean estaDisponible(Dias dia, int hora, String x) {
		if (x == ""){//si no se ingresa el nombre de ningun servicio
			return horario.estaEnHorarioDeAtencion(dia, hora);
		}
		else{
			for(Servicio serv:servicios){
				if (serv.getNombre().toUpperCase().indexOf(x.toUpperCase()) != -1){
					return horario.estaEnHorarioDeAtencion(dia, hora);
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean coincidencia(String x) {
		return Constantes.SUCURSAL_BANCO_COINCIDENCIA;
	}

	@Override
	public boolean cercanoEntre(double latitudPunto, double longitudPunto, double latitudCoordenada,
			double longitudCoordenada, int comunaId) {
		
		return (this.getRadioCercania()) >= (int) (PuntoDeInteres.distance(latitudPunto,longitudPunto,latitudCoordenada,longitudCoordenada,"K") * 1000);
	}
	
	public boolean equals(Object o){
		if (!(o instanceof LocalComercial))
			return false;
		SucursalBanco sb=(SucursalBanco) o;
		return ((servicios!=null && servicios.equals(sb.servicios) || servicios==null && sb.servicios==null) &&
				(horario!=null && horario.equals(sb.horario) || horario==null && sb.horario==null)
				&& super.equals(o));
	}


	@Override
	public PoiPojo convertir(PuntoDeInteres p) {
		SucursalBancoPojo suc = new SucursalBancoPojo();
		suc.setDireccion(p.getDireccion());
		suc.setServicios(servicios);
		return suc;
	}	
}