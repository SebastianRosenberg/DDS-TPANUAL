package tpanual.jsfcontrollers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.Utilitarios;

public class BusquedaDePoisController {
	public List<PoiPojo> buscarPois(List<String> lista){
		Iterator<String> it = lista.iterator();
		List<PuntoDeInteres> l = new ArrayList<PuntoDeInteres>();
		
		List<PoiPojo> pojos = new ArrayList<PoiPojo>();
		
		while (it.hasNext()){
			String h = it.next();
			l = Utilitarios.fusionarListasSinRepetidos(l, AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(h));
		}
		
		Iterator<PuntoDeInteres> puntos = l.iterator();
		
		while (puntos.hasNext()){
			PuntoDeInteres punto = puntos.next();
			pojos.add(punto.getPojo());
		}
		
		return pojos;
	}
}
