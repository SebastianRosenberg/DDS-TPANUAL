package tpanual.usuario.estado;

import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class Activo extends Estado{


	@Override
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test) {
		// TODO Auto-generated method stub
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x);
	}

	@Override
	public List<PuntoDeInteres> busquedaAvanzada(String x, boolean test) {
		return AdministradorDePoi.getInstance().busquedaDePuntosDeInteres(x, test);
		
	}
		
	/* Mas Informacion: segun el profesor, al buscar un poi solo deberia traer info b�sica (asumo id, nombre y direccion). 
	 * Para obtener al poi completo se llama a este metodo, pasandole el id del poi en el que estamos interesados
	 */
	@Override
	public PuntoDeInteres masInformacion(Integer id){
		return AdministradorDePoi.getInstance().masInfoDePoi(id);
	}

	
	//Como se nos pide que ahora las busquedas solo traigan informacion determinada y no solo el poi, por el momento dejo estos dos m�todos
	@Override
	public  List<PuntoDeInteres> busquedaBasica(String x, boolean test){

		return AdministradorDePoi.getInstance().busquedaBasica(x,  test);
	}
}
