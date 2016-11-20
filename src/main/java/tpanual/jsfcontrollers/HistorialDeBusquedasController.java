package tpanual.jsfcontrollers;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;

import administrador.AdministradorDeBusquedas;
import administrador.Busqueda;
import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.reportes.AdministradorDeReportes;
import tpanual.usuario.Usuario;
import tpanual.seguridad.GestorDeUsuarios;

public class HistorialDeBusquedasController {
	public List<BusquedaPojo> getHistorialBusquedas(DateTime fechaDesde, DateTime fechaHasta, String nombreUsuario){
		List<Busqueda> l;
		List<BusquedaPojo> pojos = new ArrayList<BusquedaPojo>();
		Usuario user = GestorDeUsuarios.getInstance().buscarUsuarioPorNombre(nombreUsuario);
		if (nombreUsuario == "" && (fechaDesde != null || fechaHasta != null)){
			l = AdministradorDeReportes.getBusquedasPorFecha(fechaDesde, fechaHasta);
		}else if (user != null){
			l = AdministradorDeReportes.getBusquedasPorUsuario(user);
		}else{
			return null;
		}
		
		Iterator<Busqueda> it = l.iterator();
		
		while (it.hasNext()){
			Busqueda b = it.next();
			pojos.add(b.getPojo());
		}
		return pojos;
	}
}
