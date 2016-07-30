package tpanual.factory;

import java.util.List;

import tpanual.Rubro.RubroFW;
import tpanual.main.Direccion;
import tpanual.main.Servicio;
import tpanual.main.poi.CGP;
import tpanual.main.poi.LocalComercial;
import tpanual.main.poi.ParadaColectivo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.main.poi.SucursalBanco;

public class PuntoDeInteresFactory {
	public static PuntoDeInteres getCGP(double latitud, double longitud, String nombre, Direccion direccion, List<String> palabrasClave, List<Servicio> servicios, int comunaId){
		CGP cgp=new CGP(servicios, comunaId);
		PuntoDeInteres punto=new PuntoDeInteres(latitud, longitud, nombre, direccion, palabrasClave, cgp);
		return punto;
	}
	public static PuntoDeInteres getLocalComercial(double latitud, double longitud, String nombre, Direccion direccion, List<String> palabrasClave, RubroFW rubro){
		LocalComercial l=new LocalComercial(rubro);
		PuntoDeInteres punto=new PuntoDeInteres(latitud, longitud, nombre, direccion, palabrasClave, l);
		return punto;
	}
	public static PuntoDeInteres getParadaDeColectivo(double latitud, double longitud, String nombre, Direccion direccion, List<String> palabrasClave, String linea){
		ParadaColectivo bondi=new ParadaColectivo(linea);
		PuntoDeInteres punto=new PuntoDeInteres(latitud, longitud, nombre, direccion, palabrasClave, bondi);
		return punto;
	}
	public static PuntoDeInteres getSucursal(double latitud, double longitud, String nombre, Direccion direccion, List<String> palabrasClave){
		SucursalBanco sucursal=new SucursalBanco();
		PuntoDeInteres punto=new PuntoDeInteres(latitud, longitud, nombre, direccion, palabrasClave, sucursal);
		return punto;
	}	
}
