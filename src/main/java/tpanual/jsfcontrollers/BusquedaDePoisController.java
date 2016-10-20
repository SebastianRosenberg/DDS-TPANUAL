package tpanual.jsfcontrollers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.Utilitarios;

public class BusquedaDePoisController {
	public List<PoiPojo> buscarPois(List<String> lista){
		Iterator<String> it = lista.iterator();
		List<PuntoDeInteres> l = new ArrayList<PuntoDeInteres>();
		
		while (it.hasNext()){
			String h = it.next();
			l = Utilitarios.fusionarListasSinRepetidos(l, AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(h));
		}
		
		
		
	}
}
