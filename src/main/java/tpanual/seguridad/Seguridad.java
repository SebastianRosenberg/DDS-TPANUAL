package tpanual.seguridad;

import tpanual.usuario.Usuario;

//Aclaracion/Disclaimer: falta definir una clase que conozca a todos los usuarios, en un principio pensaba dejarle esa info a mapa, por eso
// mapa tiene comentado una hashtable, pero prefer� dejarlo hasta que se decia

public class Seguridad {
	
	//revisa estado del usuario (logueado o desloguedo)
	//si est� deslogueado, tira mensaje de error y (consultar si debe hacer algo mas)
	//si esta logueado, prosigue con la busqueda, y al volver a temporizador, deberia cortar el tiempo y seguir con lo suyo
	
	public boolean Autenticacion (Usuario usuario)
	{
		if (estaLogueado(usuario))
		{
			return true;
		}
		else
		{
			System.out.println("no se pudo realizar la busqueda debido a que no se encuentra logueado");
			return false;
		}
	}
// //funcion estaLogueado, a hacer
	public boolean estaLogueado(Usuario usuario)
	{
		if(true)
		return true;
		return false;
	}
}
