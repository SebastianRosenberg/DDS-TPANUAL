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

	public static String usuario_str;

	public List<PoiPojo> buscarPois(List<String> lista) {
		Iterator<String> it = lista.iterator();
		List<PuntoDeInteres> l = new ArrayList<PuntoDeInteres>();

		List<PoiPojo> pojos = new ArrayList<PoiPojo>();

		Usuario usuario = GestorDeUsuarios.getInstance().buscarUsuarioPorNombre(usuario_str);

		if (usuario == null)
			return null;

		while (it.hasNext()) {
			String h = it.next();
			l = Utilitarios.fusionarListasSinRepetidos(l, usuario.busquedaDePuntosDeInteres(h, true));
		}

		Iterator<PuntoDeInteres> puntos = l.iterator();

		while (puntos.hasNext()) {
			PuntoDeInteres punto = puntos.next();
			pojos.add(punto.getPojo());
		}

		return pojos;
	}

	public static boolean estasLogueado() {

		if (usuario_str == null) {
			return false;
		} else {
			return true;
		}
	}
}
