package tpanual.usuario;

import java.util.List;

import administrador.AdministradorDePoi;
import tpanual.main.Direccion;
import tpanual.main.poi.PoiInfoBasica;
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
	
	/*Busqueda avanzada: busqueda en la que busca que el resultado posea todos los parametros buscados. Minimo uno de los parametros debe ser
	 * distinto de null. Necesita que el usuario, si no es admin, tenga permiso para realizar esta accion
	 */
		@Override
		public List<PuntoDeInteres> busquedaAvanzada(Usuario user, String nombre, Direccion direccion, String palabraClave, String coincDeTipo) 
		{
			if(GestorDeUsuarios.getInstance().poseePrivilegioBusquedaAvanzada(user)){
			return AdministradorDePoi.getInstance().busquedaAvanzada(nombre,direccion,palabraClave,coincDeTipo);
			}
			System.out.println("No posee el privilegio para realizar esa acción");
			return null;
			
		}
		
		/* Mas Informacion: segun el profesor, al buscar un poi solo deberia traer info básica (asumo id, nombre y direccion). 
		 * Para obtener al poi completo se llama a este metodo, pasandole el id del poi en el que estamos interesados
		 */
		@Override
		public PuntoDeInteres masInformacion(Usuario user, Integer id) 
		{
			if(GestorDeUsuarios.getInstance().poseePrivilegioMasInfo(user)){
				return AdministradorDePoi.getInstance().masInfoDePoi(id);
			}
			System.out.println("No posee el privilegio para realizar esa acción");
			return null;
		}

	
	//Como se nos pide que ahora las busquedas solo traigan informacion determinada y no solo el poi, por el momento dejo estos dos métodos
	@Override
	public List<PoiInfoBasica> realizarBusqueda(String x)
	{
		List<PuntoDeInteres> list = busquedaDePuntosDeInteres(x);
		
		return AdministradorDePoi.getInstance().mapeoPois(list);

	}
	@Override
	public  List<PoiInfoBasica> realizarBusqueda(String x, boolean test)
	{
		List<PuntoDeInteres> list = busquedaDePuntosDeInteres(x, test);
		
		return AdministradorDePoi.getInstance().mapeoPois(list);
	}
	@Override
	public  List<PoiInfoBasica> realizarBusquedaAvanzada(Usuario user, String nombre, Direccion direccion, String palabraClave, String coincDeTipo)
	{
		List<PuntoDeInteres> list = busquedaAvanzada(user, nombre, direccion, palabraClave,coincDeTipo);
		
		return AdministradorDePoi.getInstance().mapeoPois(list);
	}
}
