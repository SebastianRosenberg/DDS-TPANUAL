package tpanual.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.joda.time.Duration;
import org.joda.time.Instant;

import administrador.AdministradorDePoi;
import administrador.SesionBusqueda;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;

import tpanual.temporizador.Temporizador;

@Entity
@Table (name = "USUARIOS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario {
	
	@Id @GeneratedValue
 	@Column (name = "ID")
	protected int id;
	

	@Column (name = "USUARIO",unique = true)
	protected String nombre;	
	
	@Column (name = "privilegio")
	protected boolean privilegio;
	
	public String getUsuario(){
		return nombre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public  int getId(){
		return id;
	}

	public abstract boolean login(String password);
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar, boolean test, boolean avanzada){
		SesionBusqueda sBusqueda = new SesionBusqueda();
		Temporizador temporizador = new Temporizador();
		temporizador.tiempoInicioBusqueda();
		
		List<PuntoDeInteres> pois;
		
		pois = busquedaAvanzada(strABuscar, test);
			
		sBusqueda.setPois(pois);
		
		Duration d = temporizador.ChequeoLapso (this);
		
		sBusqueda.setDuracion(d);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar, boolean avanzada){
		return  busquedaDePuntosDeInteres(strABuscar, true, avanzada);
	}
	
	public abstract boolean isAdministrador();
	
	public abstract boolean modificarPoi(PuntoDeInteres poi);
	
	public abstract void agregarPoi(PuntoDeInteres poi);
	
	public abstract boolean eliminarPoi(PuntoDeInteres poi);
	
	public abstract void notificar();
	
	
	public abstract PuntoDeInteres masInformacion(Integer id);

	protected abstract List<PuntoDeInteres> busquedaAvanzada(String x, boolean c);
	
	protected abstract List<PuntoDeInteres> busquedaBasica(String x, boolean c);
	
	public boolean equals(Object o){
		if (o instanceof Usuario){
			Usuario u = (Usuario) o;
			return u.getId() == this.getId();
		}else{
			return false;
		}
	}
	
	//Constructor solo para Hibernate, no utilizar

	public Usuario(){
		privilegio = true;
	}
	
	public String toString(){
		return Integer.valueOf(this.getId()) + "_" + this.getUsuario();
	}


	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}