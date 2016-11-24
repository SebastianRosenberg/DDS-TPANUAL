package tpanual.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import administrador.AdministradorDePoi;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.utilitarios.Email;

@Entity
@Table (name = "Administrador")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Administrador extends Usuario{
	
	@Column (name = "EMAIL", length = 50)
	private String email;
	
	@Column (name = "PASSWORD")
	private String password;
	
	public Administrador(String email,String nombre, String password ) {
		
		this.email=email;
		this.nombre=nombre;
		this.password=password;
		
	}

	public Administrador(){
		
	}
	
	public boolean login(String password){
		return (this.password.equals(password));
	}
	
	public boolean isAdministrador(){
		return true;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public boolean modificarPoi(PuntoDeInteres poi) {
		return AdministradorDePoi.getInstance().modificarPoi(poi);
	}

	@Override
	public void agregarPoi(PuntoDeInteres poi) {
		AdministradorDePoi.getInstance().agregarPoi(poi);
	}

	@Override
	public boolean eliminarPoi(PuntoDeInteres poi) {
		return AdministradorDePoi.getInstance().eliminarPoi(poi);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public void notificar() {
		try{
			Email EnviadorMail = new Email(this.getEmail(),
                "Aviso de tardanza en busqueda", "Este es un mensaje para notificar que una busqueda tard� mas del tiempo m�ximo.");
		}catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Override
	public PuntoDeInteres masInformacion(Integer id) {
		return AdministradorDePoi.getInstance().masInfoDePoi(id);
	}

	@Override
	public List<PuntoDeInteres> busquedaAvanzada(String x, boolean test) {
		
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x, test);
	}

	@Override
	public List<PuntoDeInteres> busquedaBasica(String x, boolean test){
		return AdministradorDePoi.getInstance().busquedaBasica(x, test);
	}

	
}
