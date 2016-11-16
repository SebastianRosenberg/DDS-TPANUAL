package tpanual.seguridad;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import administrador.Mapa;
import procesos.actualizarAccionesPorUsuario.MementoUsuarios;
import tpanual.usuario.Administrador;
import tpanual.usuario.Usuario;

public class GestorDeUsuarios {
	
	private static GestorDeUsuarios instance;
	private Map<String, Usuario> usuarios;
	private String stringValidezMD5="Invalidado";
	
	public static GestorDeUsuarios getInstance(){
		if (instance==null)
			instance=new GestorDeUsuarios();
		return instance;
	}
	
	private GestorDeUsuarios(){
		usuarios = new HashMap<String, Usuario>();
		modificarMD5();
	}

	private void modificarMD5(){
		try{
			stringValidezMD5 = java.security.MessageDigest.getInstance("MD5").toString();
		}catch(NoSuchAlgorithmException n){
			System.out.println("ERROR: No se encuentra el algoritmo MD5");
		}
	}
	
	
	public Usuario crearTerminalActivo(String nombre)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioTerminalActivo(nombre);
		return nuevoUsuario;	
	}
	
	
	public Usuario crearTerminalNoActivo(String nombre)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioTerminalNoActivo(nombre);
		return nuevoUsuario;	
	}
	
	
	public  Usuario crearAdministrador(String nombre, String email, String password)
	{
		Usuario nuevoUsuario = UsuariosFactory.getUsuarioAdministrador(nombre, email, password);
		return nuevoUsuario;	
	}
	
	public Usuario logueraseAdministrador(String nombre, String password){
		Usuario u = usuarios.get(nombre);
		if (u!= null && u.login(password)){
			return u;
		}else{
			System.out.println("Usuario o password incorrecto");
			return null;
		}
	}
	
	public void setearPrivilegios(Usuario u){
		u.setPrivilegio(true);
	}
	
	public void darPrivilegioAGrupo(List<ValorPrioridades> lista){
		
		Iterator<ValorPrioridades> iterator = lista.iterator();
		while (iterator.hasNext()) {
			ValorPrioridades siguiente = iterator.next();
			siguiente.getUser().setPrivilegio(true);
		}
	}
	
	public MementoUsuarios obtenerMemento(){

		MementoUsuarios m = new MementoUsuarios(this.usuarios, this.stringValidezMD5);
		return m;
	}
	
	public void aplicarMemento(MementoUsuarios m){
		this.usuarios = m.getUsuarios();
		modificarMD5();
		
	}

	public String getStringValidezMD5() {
		return stringValidezMD5;
	}

	

}
