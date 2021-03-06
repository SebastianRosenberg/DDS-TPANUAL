package tpanual.main.poi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import tpanual.jsfcontrollers.pojos.poi.CgpPojo;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.Servicio;
import tpanual.utilitarios.Constantes;

@Entity
@Table (name = "POI_CGP")
public class CGP extends TipoPuntoInteres{
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "CGP_SERVICIOS", joinColumns = { @JoinColumn(name = "CGP_ID") }, inverseJoinColumns = { @JoinColumn(name = "SERVICIO_ID") })
	Set<Servicio> serviciosCgp;
	
	@Column (name = "COMUNA_ID")
	private String comunaId;
	
	public Set<Servicio> getServicios() {
		return serviciosCgp;
	}

	public String getComunaId() {
		return comunaId;
	}

	public CGP(List<Servicio> servicios, int comundaIid) {
		this.serviciosCgp=new HashSet<Servicio>(servicios);
		this.comunaId = String.valueOf(comundaIid);
	}
	
	public CGP(){
		
	}
	
	public int getRadioCercania(){
		return Constantes.CGP_CERCANIA;
	}

	@Override
	public boolean estaDisponible(int dia, int hora, String x) {
		boolean disponible = false;
		if (x == ""){//si no se ingresa el nombre de ningun servicio
			for(Servicio serv:serviciosCgp){
				if (serv.getHorario().estaEnHorarioDeAtencion(dia, hora)) 
					disponible=true;
			}
		}
		else{
			for(Servicio serv:serviciosCgp){
				if (serv.getNombre().toUpperCase().indexOf(x.toUpperCase()) != -1){
					disponible = serv.getHorario().estaEnHorarioDeAtencion(dia, hora);
				}
			}
		}
		return disponible;
	}
	
	public boolean coincidencia(String x){
		Iterator<Servicio> it=serviciosCgp.iterator();
		boolean aparicion=false;
		while (it.hasNext() && !aparicion){
			if (it.next().getNombre().toUpperCase().indexOf(x.toUpperCase()) != -1) 
				aparicion=true;
		}
		return aparicion;
	}

	@Override
	public boolean cercanoEntre(double latitudPunto, double longitudPunto, double latitudCoordenada,
			double longitudCoordenada, int comunaId) {
		if (this.comunaId!=null){
			return this.comunaId.equals(String.valueOf(comunaId));
		}else{
			return false;
		}
	}
	/**
	 * Recibe un servicio y te dice si esta en la lista de servicios del CGP
	 * @param x
	 * @return
	 */
	public boolean estaEnLaListaDeServicios(String x){
		Iterator<Servicio> i=serviciosCgp.iterator();
		while (i.hasNext()){
			if (i.next().getNombre().toUpperCase().indexOf(x.toUpperCase())!= -1) return true;
		}
		return false;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof CGP))
			return false;
		CGP cgp=(CGP) o;
		return ((serviciosCgp!=null && serviciosCgp.equals(cgp.serviciosCgp) || serviciosCgp==null && cgp.serviciosCgp==null) && comunaId==cgp.comunaId && super.equals(o));
	}

	@Override
	public PoiPojo convertir(PuntoDeInteres p) {
		CgpPojo cgp = new CgpPojo();
		cgp.setNombre(p.getNombre());
		cgp.setDireccion(p.getDireccion().toString());
		cgp.setLatitud(p.getLatitud());
		cgp.setLongitud(p.getLongitud());
		String serServicios = "";
		if (comunaId!=null)
			cgp.setIdComuna(Integer.valueOf(comunaId));
		if (serviciosCgp != null){
			cgp.setServicios(new ArrayList<Servicio>(serviciosCgp));
			for(Servicio unServicio : serviciosCgp){
				serServicios += unServicio.toString() + ". ";
			}
		}
		
		
		cgp.setInfoExtra("Servicios:<br/>" + serServicios);
		return cgp;
	}
}
