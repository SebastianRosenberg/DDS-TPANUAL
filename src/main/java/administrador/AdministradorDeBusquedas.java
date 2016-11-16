package administrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class AdministradorDeBusquedas {
	
	private List<Busqueda> busquedas;
	private static AdministradorDeBusquedas instance;
	
	private AdministradorDeBusquedas(){
		busquedas=new ArrayList<Busqueda>();
		//MONGO: Rellenar aca esta lista con todos los documentos en la coleccion de mongo
	}
	
	public static AdministradorDeBusquedas getInstance(){
		if (instance==null)
			instance=new AdministradorDeBusquedas();
		return instance;
	}
	
	public Busqueda getBusquedaAnterior(String[] x){
		Iterator<Busqueda> it = busquedas.iterator();
		while (it.hasNext()){
			Busqueda b=it.next();
			if (b.coincideBusqueda(x)) 
				return b;
		}
		return null;
	}
	
	public void agregarBusqueda(Busqueda b){
		busquedas.add(b);
		//MONGO: Agregar aca un nuevo documento a la coleccion de mongo
	}
		
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}

	
	
}
