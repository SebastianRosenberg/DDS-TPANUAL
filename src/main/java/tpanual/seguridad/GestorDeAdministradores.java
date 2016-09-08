package tpanual.seguridad;

import java.util.Hashtable;
import java.util.List;

import administrador.Mapa;
import tpanual.factory.UsuariosFactory;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class GestorDeAdministradores {
	
	private static GestorDeAdministradores instance;
	private static Hashtable<Usuario,String> admins;
	
	
	public static GestorDeAdministradores getInstance(){
		if (instance==null)
			instance=new GestorDeAdministradores();
		return instance;
	}
	
	//esto creo que no esta bien
	
		public static Hashtable<Usuario,String> getHash(){
			if (admins==null)
				admins=new Hashtable<Usuario,String>();
			return admins;
		}
	
	
	public static Usuario crearAdministrador(String nombre, String email, int id, String password )
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador(nombre, email ,id);
		admins.put(nuevoUsuario, password);
		return nuevoUsuario;	
	}
	
	public static Usuario LogueoAdmin(Usuario user, String passwordIngresada)
	{
		String pass = admins.get(user);
		if (passwordIngresada == pass)
		{
			return user;
		}
		else
		{
			System.out.println("contraseña equivocada, por favor intente nuevamente");
			return null;
		}
	}
}
