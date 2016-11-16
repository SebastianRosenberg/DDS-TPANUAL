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
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;

import tpanual.temporizador.Temporizador;

@Entity
@Table (name = "USUARIOS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario {
	
	@Id @GeneratedValue
 	@Column (name = "ID")
	protected int id;
	

	@Column (name = "USUARIO")
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
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar, boolean test){
		SesionBusqueda sBusqueda = new SesionBusqueda();
		Temporizador temporizador = new Temporizador();
		temporizador.tiempoInicioBusqueda();

		List<PuntoDeInteres> pois =AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(strABuscar, test);
		sBusqueda.setPois(pois);
		
		Duration d = temporizador.ChequeoLapso (this);
		
		sBusqueda.setDuracion(d);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar){
		return this.busquedaDePuntosDeInteres(strABuscar, true);
	}
	
	public abstract boolean isAdministrador();
	
	public abstract boolean modificarPoi(PuntoDeInteres poi);
	
	public abstract void agregarPoi(PuntoDeInteres poi);
	
	public abstract boolean eliminarPoi(PuntoDeInteres poi);
	
	public abstract void notificar();
	
	
	public abstract PuntoDeInteres masInformacion(Integer id);

	public abstract List<PuntoDeInteres> busquedaAvanzada(String x, boolean c);
	
	public List<PuntoDeInteres> busquedaAvanzada(String x){
		return busquedaAvanzada(x, true);
	}
	
	public abstract List<PoiInfoBasica> busquedaBasica(String x, boolean c);
	
	public List<PoiInfoBasica> busquedaBasica(String x){
		return busquedaBasica(x, true);
	}
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}
}