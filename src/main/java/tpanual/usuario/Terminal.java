package tpanual.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.estado.Activo;
import tpanual.usuario.estado.Estado;
import tpanual.usuario.estado.NoActivo;

@Entity
@Table (name = "Terminal")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Terminal extends Usuario {

	
	@Transient
	private Estado estado;
	
	public boolean isAdministrador(){
		return false;
	}

	public void activar() {
		this.estado = new Activo();
	}
	
	public void desactivar() {
		this.estado = new NoActivo();
		
	}
	
	public boolean login(String password){
		if (password.equals(""))
			return true;
		else
			return false;
	}

	@Override
	public boolean modificarPoi(PuntoDeInteres poi) {
		return false;
	}
	@Override
	public void agregarPoi(PuntoDeInteres poi) {
		
	}
	@Override
	public boolean eliminarPoi(PuntoDeInteres poi) {
		return false;
	}
	
	public Terminal(String nombre,Estado estado,int Id){
		
		this.estado = estado;
		this.setId(Id);
		this.setNombre(nombre);
		
	}

	public void setNombre(String nombre) {
	
		this.nombre = nombre;
	
	}

	@Override
	public void notificar() {

		
	}
	@Override
	public PuntoDeInteres masInformacion(Integer id) {
		return this.estado.masInformacion(id);
	}
	@Override
	public List<PuntoDeInteres> busquedaAvanzada(String x, boolean c) {
		if (this.privilegio){
			return this.estado.busquedaAvanzada(x, c);
		}else{
			return null;
		}
	}

	@Override
	public List<PuntoDeInteres> busquedaBasica(String x, boolean c){
		return this.estado.busquedaBasica(x, c);
	}

	
	public Terminal(){
		
	}
}
