package administrador;

import java.util.Iterator;
//import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
/**
 * Se crea una nueva instancia antes de comenzar la busqueda. A través de las etapas que atraviece la busqueda, esta instancia debe ser pasada por parametro.
 * Se van agregando los distintos atributos que la busqueda puede tener:
 * 
 * usuario que realizo la busqueda
 * duracion de la busqueda
 * pois que encontro
 * strings que se buscaron
 * 
 * Al terminar todas las etapas de la busqueda, se invoca el metodo finalizarBusqueda(), este metodo agrega la busqueda al administrador
 * 
 * 
 * @author dipatata
 *
 */

public class SesionBusqueda {
	
	private Usuario usuario;
	private Duration duracion;
	private List<PuntoDeInteres> pois;
	private String[] stringsBuscados;
	private DateTime inicioDeBusqueda;
	
	public SesionBusqueda(){
		inicioDeBusqueda = new DateTime();
	}
	
	
	/*
	 * Se modifica método para hacerlo coincidir con el nuevo constructor
	 * de la clase Busqueda
	 */
	public void finalizarBusqueda(){
		
		if (pois!=null && !pois.isEmpty()){
			int[] ints=new int[0];
			
			Iterator<PuntoDeInteres> i=pois.iterator();
			
			while (i.hasNext()){
				PuntoDeInteres poi=i.next();
				ints=agregarElemento(ints, poi.getId());
			}			
			Busqueda b=new Busqueda(stringsBuscados, ints, usuario, duracion, inicioDeBusqueda);
			AdministradorDeBusquedas.getInstance().agregarBusqueda(b);
		}
	}
	

/*public void finalizarBusqueda(){
		
		if (pois!=null && !pois.isEmpty()){			
			Busqueda b=new Busqueda(stringsBuscados, pois, usuario, duracion, inicioDeBusqueda);
			AdministradorDeBusquedas.getInstance().agregarBusqueda(b);
		}
	}*/

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}

	public void setPois(List<PuntoDeInteres> pois) {
		this.pois = pois;
	}

	public void setStringsBuscados(String[] stringsBuscados) {
		this.stringsBuscados = stringsBuscados;
	}
	
	private int[] agregarElemento(int[] lista, int ele){
		int c=0;
		for (int y=0;y<lista.length;y++){
			c++;
		}
		int[] nuevo=new int[c+1];
		for (int a=0;a<lista.length;a++){
			nuevo[a]=lista[a];
		}		
		nuevo[c]=ele;
		return nuevo;
	}	
	
	//se agrega metodo para devolver una busqueda y persistirla en un test
	/*
	 * Identico caso al método anterior, se modifica método para pasar
	 * por parámetro la lista de pois encontrados y no la lista de ids
	 */
	public Busqueda obtenerBusqueda(){
		Busqueda b = null;
		if (pois!=null && !pois.isEmpty()){
			int[] ints=new int[0];
			
			Iterator<PuntoDeInteres> i=pois.iterator();
			
			while (i.hasNext()){
				PuntoDeInteres poi=i.next();
				ints=agregarElemento(ints, poi.getId());
			}			
			b=new Busqueda(stringsBuscados, ints, usuario, duracion, inicioDeBusqueda);
			AdministradorDeBusquedas.getInstance().agregarBusqueda(b);
		}
		return b;
	}
	

	/*
	public Busqueda obtenerBusqueda(){
		Busqueda b = null;
		if (pois!=null && !pois.isEmpty()){			
			b=new Busqueda(stringsBuscados, pois, usuario, duracion, inicioDeBusqueda);
			AdministradorDeBusquedas.getInstance().agregarBusqueda(b);
		}
		return b;
	}*/
	
	
	
	
}
