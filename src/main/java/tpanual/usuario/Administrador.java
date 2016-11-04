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
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.utilitarios.Email;

@Entity
@Table (name = "Administrador")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Administrador extends Usuario{

	//@Id @GeneratedValue
 	//@Column (name = "ID")
	//private int id;
	
	//private String nombre;
	@Column (name = "EMAIL", length = 50)
	private String email;
	
	@Override
	public String getUsuario() {
		// TODO Auto-generated method stub
		return nombre;
	}

	//@Override
	/*public int getId() {
		// TODO Auto-generated method stub
		return id;
	}*/

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public void activar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<PuntoDeInteres> buscarPuntos(String x) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x);
	}

	@Override
	protected List<PuntoDeInteres> buscarPuntos(String x, boolean test) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x, test);
	}

	@Override
	public boolean modificarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().modificarPoi(poi);
	}

	@Override
	public void agregarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		AdministradorDePoi.getInstance().agregarPoi(poi);
	}

	@Override
	public boolean eliminarPoi(PuntoDeInteres poi) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().eliminarPoi(poi);
	}

	public Administrador(String email, int id,String nombre ) {
		
		this.setEmail(email);
		this.setId(id);
		this.setNombre(nombre);
		
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
	public Usuario desloguear(Usuario usuario)
	{	
			Usuario terminal = GestorDeUsuarios.getInstance().deslogueoAdmin(usuario);
			return terminal;

	}
	

	@Override
	public Usuario logueo(Usuario usuario, String password, Usuario terminal) {
		// TODO Auto-generated method stub
		 System.out.println("Ya se encuentra logueado");
		 return usuario;
	}

	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		Email EnviadorMail = new Email(this.getEmail(),
                "Aviso de tardanza en busqueda", "Este es un mensaje para notificar que una busqueda tard� mas del tiempo m�ximo.");
	}
	
	@Override
	public PuntoDeInteres masInformacion(Usuario user, Integer id) {
		return AdministradorDePoi.getInstance().masInfoDePoi(id);
	}

	@Override
	public List<PuntoDeInteres> busquedaAvanzada(Usuario usuarioAProbar, String string, Direccion direccion,
			String string2, String string3) {
		
		return AdministradorDePoi.getInstance().busquedaAvanzada(string, direccion, string2, string3);
	}

	
	//Como se nos pide que ahora las busquedas solo traigan informacion determinada y no solo el poi, por el momento dejo estos dos m�todos
	
		@Override
		public List<PoiInfoBasica> realizarBusqueda(String x)
		{
			List<PuntoDeInteres> list = busquedaDePuntosDeInteres(x);
			
			return AdministradorDePoi.getInstance().mapeoPois(list);
		}
		@Override
		public  List<PoiInfoBasica> realizarBusqueda(String x, boolean test)
		{
			List<PuntoDeInteres> list = busquedaDePuntosDeInteres(x, test);
			
			return AdministradorDePoi.getInstance().mapeoPois(list);
		}
		@Override
		public  List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion, String palabraClave, String coincDeTipo)
		{
			List<PuntoDeInteres> list = busquedaAvanzada(user, nombre, direccion, palabraClave,coincDeTipo);
			
			return AdministradorDePoi.getInstance().mapeoPois(list);
		}

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return super.id;
		}
	
		//Constructor solo para Hibernate, no utilizar
		public Administrador(){
			
		}
	
}
