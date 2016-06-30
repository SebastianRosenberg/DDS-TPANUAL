package tpanual.main;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;



public class PuntoDeInteres {
	double latitud;
	double longitud;
	String nombre;
	Direccion direccion;
	TipoPuntoInteres tipo;
	List<String> palabrasClaves;
	
	public PuntoDeInteres(double latitud, double longitud, String nombre, Direccion direccion, List<String> palabrasClaves, TipoPuntoInteres tipo) {
		this.latitud=latitud;
		this.longitud=longitud;
		this.nombre=nombre;
		this.direccion=direccion;
		this.tipo=tipo;
		this.palabrasClaves=palabrasClaves;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public String getNombre() {
		return nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public TipoPuntoInteres getTipo() {
		return tipo;
	}

	public String coordenadas(){
		return this.latitud + "," + this.longitud;
	}

	public boolean cercanoA(int metros, PuntoDeInteres otroPoi){
		Mapa map1 = new Mapa();
		//Double distancia;
		return metros >= (int) (map1.distance(this.getLatitud(),this.getLongitud(),otroPoi.getLatitud(),otroPoi.getLongitud(),"K") * 1000);
	//	return true;
		
	}
	
	public boolean buscarCoincidencia(String x){
		
		return Utilitarios.buscarPalabraEnUnaLista(x, palabrasClaves) || tipo.coincidencia(x)|| (nombre.indexOf(x) != -1);
	}

	public boolean estaDisponible(Dias dia, int hora, String x) {
		return tipo.estaDisponible(dia, hora, x);
	}

}

