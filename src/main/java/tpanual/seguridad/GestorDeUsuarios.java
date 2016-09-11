package tpanual.seguridad;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import administrador.Mapa;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class GestorDeUsuarios {
	
	private static GestorDeUsuarios instance;
	private  Hashtable<Usuario, String> hashAdmins;
	private  Hashtable<Integer, Usuario> adminsLogueados;
	private  Hashtable<Integer, Usuario> terminales;
	private  Hashtable<Integer, Usuario> administradores;
	
	public static GestorDeUsuarios getInstance(){
		if (instance==null)
			instance=new GestorDeUsuarios();
		return instance;
	}
	
	private GestorDeUsuarios(){
		hashAdmins=new Hashtable<Usuario, String>();
		adminsLogueados=new Hashtable<Integer, Usuario>();
		terminales=new Hashtable<Integer, Usuario>();
		administradores=new Hashtable<Integer, Usuario>();
		 }
	
	public Hashtable<Usuario, String> getHashAdmins() {
		return hashAdmins;
	}
	
	public Hashtable<Integer, Usuario> getAdminsLogueados() {
		return adminsLogueados;
	}
	
	public Hashtable<Integer, Usuario> getTerminales() {
		return terminales;
	}
	
	public Hashtable<Integer, Usuario> getAdministradores() {
		return administradores;
	}
	
	/** Creacion de los distintos tipos de usuarios.
	 *  Se llama a los respectivos factories
	 */
	
	
	public Usuario crearTerminalActivo(String nombre)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioTerminalActivo(nombre);
		getTerminales().put(nuevoUsuario.getId(), nuevoUsuario);
		return nuevoUsuario;	
	}
	
	
	public Usuario crearTerminalNoActivo(String nombre)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioTerminalNoActivo(nombre);
		getTerminales().put(nuevoUsuario.getId(), nuevoUsuario);
		return nuevoUsuario;	
	}
	
	
	public  Usuario crearAdministrador(String nombre, String email, String password)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador(nombre, email);
		getAdministradores().put(nuevoUsuario.getId(), nuevoUsuario);
		getHashAdmins().put(nuevoUsuario, password);
		return nuevoUsuario;	
	}
	
	/**Si alguien intenta loguearse como admin y alguien más está logueado, echa a la persona que ya esta logueada
	 */
	
	
	public  Usuario logueoComoAdmin(Usuario admin, String passwordIngresada, Usuario terminalActual)
	{
			String pass = getHashAdmins().get(admin);
			if (passwordIngresada != null && passwordIngresada.equals(pass))
			{
				if(getAdminsLogueados().get(admin.getId())!=null)
				{
					//revisar bien
					deslogueoAdmin(admin);			
				}
				getAdminsLogueados().put(admin.getId(), terminalActual);
				return admin;
			}
			else
			{
				System.out.println("contraseña equivocada, por favor intente nuevamente");
				return null;
			}
			
	}
	
		
	public Usuario deslogueoAdmin (Usuario admin)
	
	{
		
		Usuario terminal = (Usuario) getAdminsLogueados().get(admin.getId());
		
		if(terminal != null){
			
			getAdminsLogueados().remove(admin.getId());
			
			return terminal;
		}
		
		System.out.println("Se detecto un error, no se encuentra su terminal en el registro");
		
		return null;
		
	}
	
	
	public Usuario getTerminalPorID(int id)	
	
	{
		
		return terminales.get(id);
		
	}
	
	
	public Usuario getAdministradoresPorID(int id)	
	
	{
		
		return administradores.get(id);
		
	}
}
