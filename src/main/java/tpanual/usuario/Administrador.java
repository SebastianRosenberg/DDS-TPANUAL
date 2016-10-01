package tpanual.usuario;

import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.utilitarios.Email;

public class Administrador extends TipoDeUsuario{

	private String nombre;
	private int id;
	private String email;
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

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
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x);
	}

	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
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

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public Usuario desloguear(Usuario usuario)
	{	
			Usuario terminal = GestorDeUsuarios.getInstance().deslogueoAdmin(usuario);
			return terminal;

	}
	

	@Override
	public Usuario loguear(Usuario usuario, String password, Usuario terminal) {
		// TODO Auto-generated method stub
		 System.out.println("Ya se encuentra logueado");
		 return usuario;
	}

	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		Email EnviadorMail = new Email(this.getEmail(),
                "Aviso de tardanza en busqueda", "Este es un mensaje para notificar que una busqueda tardó mas del tiempo máximo.");
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

	
	
}
