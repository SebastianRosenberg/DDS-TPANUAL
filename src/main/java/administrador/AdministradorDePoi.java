package administrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.joda.time.Duration;

import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;
import tpanual.utilitarios.Utilitarios;

/**
 * 
 * @author dipatata
 *@usoBufferBusqueda: Nos dice si la ultima busqueda realizada utilizo el buffer de busquedas o realizo efectivamente la busqueda
 *
 */

public class AdministradorDePoi {

	
	private boolean usoBufferBusqueda;
	private static AdministradorDePoi instance;
	
	public boolean modificarPoi(PuntoDeInteres poi){
		return Utilitarios.getHibernateFactorySessions().modificarPuntoDeInteres(poi);
	}
	
	public void agregarPoi(PuntoDeInteres poi){
		Utilitarios.getHibernateFactorySessions().add(poi);
	}

	public int agregarPoiFuentesExternas(PuntoDeInteres poi){
		return Utilitarios.getHibernateFactorySessions().add(poi);
	}
	
	public boolean eliminarPoi(PuntoDeInteres poi){
		return (Utilitarios.getHibernateFactorySessions().eliminarPuntoDeInteres(poi) == 1);
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x){
		return busquedaDePuntosDeInteres(x, false);
	}
	
	public PuntoDeInteres obtenerPoiPorId(int id){
		return Utilitarios.getHibernateFactorySessions().obtenerPoi(id);
	}
	
	/**
	 * 
	 * @param x
	 * @param test: Si es true, busca en el mock en vez de servicios externos
	 * @return
	 */
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x, boolean test){
		String listaStrings[] = {x};
		Busqueda busqueda=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaStrings);
		List<PuntoDeInteres> lista;
		if (busqueda!=null){
			usoBufferBusqueda=true;
//			try{
//				return this.devolverPoiPorIds(busqueda.getIdsEncontrados());
//			}catch(PuntoDeInteresNoEncontradoException e){
//				//Se invalida la busqueda anterior y se procede a buscar normalmente
//			}
			return busqueda.getPoiEncontrados();
		}else{
			usoBufferBusqueda=false;
		}
		lista=Mapa.getInstance().buscarPuntosDeInteresEnHibernate(x);
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarEnFuentesExternas(x, test));
		return lista;
	}
	
	private List<PuntoDeInteres> devolverPoiPorIds(int... id) throws PuntoDeInteresNoEncontradoException{
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		for (int i=0;i<id.length;i++){
			PuntoDeInteres poi = Utilitarios.getHibernateFactorySessions().obtenerPoi(id[i]);
			if (poi==null)
				throw new PuntoDeInteresNoEncontradoException("Uno de los ids de la busqueda ya no se encuentra en memoria.");
			lista.add(poi);
		
		}
		return lista;
	}
	@Deprecated
	public List<PuntoDeInteres> buscarBancos(String banco, String servicio ){
		return buscarBancos(banco, servicio, false);
	}
	@Deprecated
	public List<PuntoDeInteres> buscarBancos(String banco, String servicio, boolean test){
		String listaStrings[] = {banco, servicio};
		Busqueda busqueda=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaStrings);
		List<PuntoDeInteres> lista;
		if (busqueda!=null){
			usoBufferBusqueda=true;
//			try{
//				return this.devolverPoiPorIds(busqueda.getIdsEncontrados());
//			}catch(PuntoDeInteresNoEncontradoException e){
//				//Se invalida la busqueda anterior y se procede a buscar normalmente
//			}
			return busqueda.getPoiEncontrados();
		}else{
			usoBufferBusqueda=false;
		}
		lista=Mapa.getInstance().buscarPuntosDeInteresEnHibernate(banco);
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarPuntosDeInteresEnHibernate(servicio));
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarBancos(banco, servicio));
		return lista;
	}

	public boolean usoBufferBusqueda() {
		return usoBufferBusqueda;
	}
	

	/*
	 * 
	 * Agrego el m�todo para obtener la instancia del administrador de Pois
	 * 
	 */
	
	public static AdministradorDePoi getInstance(){
		if (instance==null)
			instance=new AdministradorDePoi();
		return instance;
	}
	
	
	
	/*
	 *  Metodos entrega 4
	 * 
	 * 
	 */

	public PuntoDeInteres masInfoDePoi(Integer id) {
		PuntoDeInteres poi =Utilitarios.getHibernateFactorySessions().obtenerPoi(id);
		return poi;
	}

	
	public List<PuntoDeInteres> busquedaBasica(String x, boolean c){
		List<PuntoDeInteres> pois = this.busquedaDePuntosDeInteres(x, c);
		List<PuntoDeInteres> lista = new ArrayList<PuntoDeInteres>();
		Iterator<PuntoDeInteres> iterator = pois.iterator();
		while (iterator.hasNext()) {
			PuntoDeInteres poi = new PuntoDeInteres();
			PuntoDeInteres poiEnLista = iterator.next();
			poi.setId(poiEnLista.getId());
			poi.setDireccion(poiEnLista.getDireccion());
			poi.setNombre(poiEnLista.getNombre());
			lista.add(poi);
		}
		return lista;
	}
	
	public List<PuntoDeInteres> busquedaBasica(String x){
		return busquedaBasica(x, true);
	}
	
	
	 public int[] intersection(int x[],int y[])
	    {	int c = 0;
	    	int j = 0;
	        int []z=new int[x.length+y.length];
	        for(int i=0;i <(x.length);i++)
	        {
	            for(j=0;j < y.length;j++)
	            {
	                if(x[i]==y[j])
	                {
	                    z[c]=x[i];  
	                    c++;
	                }              
	            }
	        }
	        return z;
	    }

	public boolean isUsoBufferBusqueda() {
		return usoBufferBusqueda;
	}
	
}
