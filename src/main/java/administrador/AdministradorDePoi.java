package administrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.joda.time.Duration;

import tpanual.main.Direccion;
import tpanual.main.poi.PoiInfoBasica;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
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
		PuntoDeInteres aModificar=Mapa.getInstance().obtenerPuntoDeInteres(poi.getId());
		if (aModificar!=null)
			return aModificar.afectarCambios(poi);
		return false;
	}
	
	public void agregarPoi(PuntoDeInteres poi){
		Mapa.getInstance().agregarPunto(poi);
	}
	public boolean eliminarPoi(PuntoDeInteres poi){
		return (Mapa.getInstance().eliminarPunto(poi.getId()) != null);
	}
	
	public List<PuntoDeInteres> busquedaDePuntosDeInteres(String x){
		return busquedaDePuntosDeInteres(x, false);
	}
	
	public PuntoDeInteres obtenerPoiPorId(int id){
		return Mapa.getInstance().obtenerPuntoDeInteres(id);
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
			try{
				this.devolverPoiPorIds(busqueda.getIdsEncontrados());
			}catch(PuntoDeInteresNoEncontradoException e){
				//Se invalida la busqueda anterior y se procede a buscar normalmente
			}
		}else{
			usoBufferBusqueda=false;
		}
		lista=Mapa.getInstance().buscarPuntosDeInteresEnMemoria(x);
		
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarEnFuentesExternas(x, test));
		return lista;
	}
	
	private List<PuntoDeInteres> devolverPoiPorIds(int... id) throws PuntoDeInteresNoEncontradoException{
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		for (int i=0;i<id.length;i++){
			PuntoDeInteres poi=Mapa.getInstance().obtenerPuntoDeInteres(id[i]);
			if (poi==null)
				throw new PuntoDeInteresNoEncontradoException("Uno de los ids de la busqueda ya no se encuentra en memoria.");
			lista.add(poi);
		
		}
		return lista;
	}

	public List<PuntoDeInteres> buscarBancos(String banco, String servicio ){
		return buscarBancos(banco, servicio, false);
	}
	
	public List<PuntoDeInteres> buscarBancos(String banco, String servicio, boolean test){
		String listaStrings[] = {banco, servicio};
		Busqueda busqueda=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaStrings);
		List<PuntoDeInteres> lista;
		if (busqueda!=null){
			usoBufferBusqueda=true;
			try{
				return this.devolverPoiPorIds(busqueda.getIdsEncontrados());
			}catch(PuntoDeInteresNoEncontradoException e){
				//Se invalida la busqueda anterior y se procede a buscar normalmente
			}
		}else{
			usoBufferBusqueda=false;
		}
		lista=Mapa.getInstance().buscarPuntosDeInteresEnMemoria(banco);
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarPuntosDeInteresEnMemoria(servicio));
		lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarBancos(banco, servicio));
		return lista;
	}

	public boolean usoBufferBusqueda() {
		return usoBufferBusqueda;
	}
	

	/*
	 * 
	 * Agrego el método para obtener la instancia del administrador de Pois
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
		PuntoDeInteres poi =Mapa.getInstance().obtenerPuntoDeInteres(id);
		return poi;
	}

	public List<PuntoDeInteres> busquedaAvanzada(String nombre, Direccion direccion, String palabraClave, String coincDeTipo) {
		if((nombre==null)&&(direccion==null)&&(palabraClave==null)&&(coincDeTipo==null)){
			System.out.println("no ha ingresado ningun valor");
			return null;
		}
		String listaNombre[] = {nombre};
		String listaPalabraClave[] = {palabraClave};
		String listaCoincDeTipo[] = {coincDeTipo};
		
//		revisar busqueda
		Busqueda busqueda1=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaNombre);
		Busqueda busqueda2=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaPalabraClave);
		Busqueda busqueda3=AdministradorDeBusquedas.getInstance().getBusquedaAnterior(listaCoincDeTipo);
		
		List<PuntoDeInteres> lista;
		if (busqueda1!=null&&busqueda2!=null&&busqueda3!=null){
			usoBufferBusqueda=true;
			try{
				int[] listaAAnalizar =intersection(intersection 
						(busqueda1.getIdsEncontrados(),busqueda2.getIdsEncontrados()),busqueda3.getIdsEncontrados());
				this.devolverPoiPorIds(listaAAnalizar);
			}catch(PuntoDeInteresNoEncontradoException e){
			}
		}else{
			usoBufferBusqueda=false;
		}
		lista=Mapa.getInstance().BusquedaAvanzadaEnMemoria(nombre,direccion,palabraClave,coincDeTipo);
		
		//revisar si me sirven 
		//lista=Utilitarios.fusionarListasSinRepetidos(lista, Mapa.getInstance().buscarEnFuentesExternas(x, test));
		return lista;
	}
	
	
	public List<PoiInfoBasica> mapeoPois(List<PuntoDeInteres> listaDePois)
	{
		PoiInfoBasica poi = new PoiInfoBasica();
		List<PoiInfoBasica> lista = new ArrayList<PoiInfoBasica>();
		Iterator<PuntoDeInteres> iterator = listaDePois.iterator();
		while (iterator.hasNext()) {
			PuntoDeInteres poiEnLista = iterator.next();
			poi.setId(poiEnLista.getId());
			poi.setDireccion(poiEnLista.getDireccion());
			poi.setNombre(poiEnLista.getNombre());
			lista.add(poi);
		}
		return lista;
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
	
}
