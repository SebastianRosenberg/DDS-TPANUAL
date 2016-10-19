package tpanual.webservices.recursos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tpanual.main.poi.PuntoDeInteres;

public class TraductorDePojo {
	public List<PoiPojo> traducirPoisAPojos(List<PuntoDeInteres> lista){
		List<PoiPojo> listaADevolver = new ArrayList<PoiPojo>();
		Iterator<PuntoDeInteres> it = lista.iterator();
		while (it.hasNext()){
			PuntoDeInteres pdi = it.next();
			listaADevolver.add(new PoiPojo(pdi.getId()));
		}
		return listaADevolver;
	}
}
