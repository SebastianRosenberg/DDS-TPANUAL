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
	
	public void setId(int id) {
		this.id = id;
	}

	@Column (name = "USUARIO")
	protected String nombre;
	
	public abstract String getEmail();
	
	public abstract String getUsuario();
	
	public abstract int getId();
	
	
	public abstract void activar();
	
	public abstract void desactivar();
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar){
	   
		SesionBusqueda sBusqueda = new SesionBusqueda();
		
		Instant inicio = Temporizador.TiempoInicioBusqueda();

		List<PuntoDeInteres> pois = this.buscarPuntos(strABuscar); 
		sBusqueda.setPois(pois);
		
		Duration duracion = Temporizador.LapsoBusqueda(inicio);
		
		Temporizador.ChequeoLapso (duracion, this);
		
		sBusqueda.setDuracion(duracion);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String strABuscar, boolean test){
		SesionBusqueda sBusqueda = new SesionBusqueda();
		Temporizador temporizador = new Temporizador();
		Instant inicio = temporizador.TiempoInicioBusqueda();

		List<PuntoDeInteres> pois =this.buscarPuntos(strABuscar, test); 
		sBusqueda.setPois(pois);
		
		Duration duracion = temporizador.LapsoBusqueda(inicio);
		Temporizador.ChequeoLapso (duracion, this);
		
		sBusqueda.setDuracion(duracion);
		sBusqueda.setStringsBuscados(new String[] {strABuscar});
		sBusqueda.setUsuario(this);
		sBusqueda.finalizarBusqueda();
		return pois; 
		
	}
	
	protected abstract List<PuntoDeInteres> buscarPuntos(String x);
	protected abstract List<PuntoDeInteres> buscarPuntos(String x, boolean test);
	
	public abstract boolean modificarPoi(PuntoDeInteres poi);
	
	public abstract void agregarPoi(PuntoDeInteres poi);
	
	public abstract boolean eliminarPoi(PuntoDeInteres poi);
	
	public abstract Usuario logueo(Usuario admin,String pass,Usuario term);
	
	public String toString(){
		return Integer.valueOf(this.getId()) + "_" + this.getUsuario();
	}
	
	public abstract void notificar();
	
	
	public abstract PuntoDeInteres masInformacion(Usuario user, Integer id);

	public abstract List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion, String string2,
			String string3);
	
	
	public abstract List<PoiInfoBasica> realizarBusqueda(String x);

	public abstract List<PoiInfoBasica> realizarBusqueda(String x, boolean test);

	public abstract List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion, String palabraClave, String coincDeTipo);
	
	public boolean equals(Object o){
		if (o instanceof Usuario){
			Usuario u = (Usuario) o;
			return u.getId() == this.getId();
		}else{
			return false;
		}
	}

	public abstract Usuario desloguear(Usuario usuario);
	
	//Constructor solo para Hibernate, no utilizar
			public Usuario(){
				
			}

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}
}