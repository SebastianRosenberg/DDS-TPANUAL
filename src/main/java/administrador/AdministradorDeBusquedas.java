package administrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.mongo.MongoDBConnection;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

public class AdministradorDeBusquedas {
	
	private List<Busqueda> busquedas;
	private static AdministradorDeBusquedas instance;
	
	private AdministradorDeBusquedas(){
		busquedas=new ArrayList<Busqueda>();
	}
	
	public static AdministradorDeBusquedas getInstance(){
		if (instance==null)
			instance=new AdministradorDeBusquedas();
		return instance;
	}
	
	public Busqueda getBusquedaAnterior(String[] x){
		
		/*: BUSCA EN MEMORIA, YA NO LO HACE
		Iterator<Busqueda> it = busquedas.iterator();
		while (it.hasNext()){
			Busqueda b=it.next();
			if (b.coincideBusqueda(x)) 
				return b;
		}
		return null;
		*/
		return MongoDBConnection.getInstance().obtenerBusquedas(x).get(0);
		
	}
	
	public void agregarBusqueda(Busqueda b){
		busquedas.add(b);
		MongoDBConnection.getInstance().agregarBusqueda(b);
	}
		
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}
	
}
