package tpanual.usuario;

import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;

public class Activo extends Estado{


	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x);
	}

	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x);
	}
	
	public PuntoDeInteres masInformacion(Usuario user, Integer id) {
		if(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(user)){
		return AdministradorDePoi.getInstance().masInfoDePoi(id);
		}
		System.out.println("No posee el privilegio para realizar esa acción");
		return null;
	}

}
