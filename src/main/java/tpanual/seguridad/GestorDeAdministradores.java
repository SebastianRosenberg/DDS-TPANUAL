package tpanual.seguridad;

import java.util.Hashtable;
import java.util.List;

import administrador.Mapa;
import tpanual.factory.UsuariosFactory;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class GestorDeAdministradores {
	
	private static GestorDeAdministradores instance;
	private static Hashtable<Usuario, String> admins;
	private static Hashtable<Integer, Usuario> usuariosLogueados;
	
	public static GestorDeAdministradores getInstance(){
		if (instance==null)
			instance=new GestorDeAdministradores();
		return instance;
	}
	
	
	public static Usuario crearAdministrador(String nombre, String email, int id, String password )
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador(nombre, email ,id);
		admins.put(nuevoUsuario, password);
		return nuevoUsuario;	
	}
	
	public Usuario logueoAdmin(Usuario user, String passwordIngresada, Usuario term)
	{
		if(chequeoNoLogueado(user)){
			String pass = admins.get(user);
			if (passwordIngresada == pass)
			{
				usuariosLogueados.put((user.getId()) ,term);
				return user;
			}
			else
			{
				System.out.println("contraseña equivocada, por favor intente nuevamente");
				return null;
			}
		}
		else
		{
			System.out.println("Error: esa cuenta ya se encuentra logueada");
			return null;
		}
	}
	
	public static boolean chequeoNoLogueado(Usuario user)
	{
		if(usuariosLogueados.get(user)!=null){
		return false;	
		}
		return true;
	}
	
	public Usuario deslogueoAdmin (Usuario admin)
	{
		Usuario terminal = usuariosLogueados.get(admin.getId());
		if(terminal != null){
			return terminal;
		}
		System.out.println("Se detecto un error, no se encuentra su terminal en el registro");
		return null;
		
	}
	
}
