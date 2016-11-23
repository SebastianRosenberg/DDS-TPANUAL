package tpanual.jsfcontrollers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Utilitarios;

public class BusquedaDePoisController {
	public List<PoiPojo> buscarPois(List<String> lista, String usuario_string){
		Iterator<String> it = lista.iterator();
		List<PuntoDeInteres> l = new ArrayList<PuntoDeInteres>();
		
		List<PoiPojo> pojos = new ArrayList<PoiPojo>();
		
		Usuario usuario = GestorDeUsuarios.getInstance().buscarUsuarioPorNombre(usuario_string);
		
		while (it.hasNext()){
			String h = it.next();
			l = Utilitarios.fusionarListasSinRepetidos(l, usuario.busquedaDePuntosDeInteres(h, true));
		}
		
		Iterator<PuntoDeInteres> puntos = l.iterator();
		
		while (puntos.hasNext()){
			PuntoDeInteres punto = puntos.next();
			pojos.add(punto.getPojo());
		}
		
		return pojos;
	}
}
