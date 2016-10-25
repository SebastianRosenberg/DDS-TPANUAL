package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tpanual.jsfcontrollers.BusquedaDePoisController;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;

@ManagedBean
@ViewScoped
public class BusquedaDePoiBean {
	 private List<Item> items;
	 private List<PoiPojo> pojos = new ArrayList<PoiPojo>();
	 private List<String> datos = new ArrayList<String>();
	 private int id=0;;
	 
	    @PostConstruct
	    public void init() {
	        items = new ArrayList<Item>();
	    }

	    public void add() {
	        Item i = new Item();
	        i.setId(this.id);
	        id++;
	        items.add(i);
	    }

	    public void remove(Item item) {
	        items.remove(item);
	    }

	    public void save(Item item) {
	       //buscarPoiService
	    	//armarlista
	    	//items.update(item);
	    	System.out.println("items: " + items);
	    }

	    public List<Item> getItems() {
	        return items;
	    }
	    
	    public void buscar(String datosEntrada){
	    	//parseo el string de entrada
	    	//List<String> datos = Arrays.asList(datosEntrada.split("\\s*,\\s*"));
	    	//instancio la clase que llama al backend
	    	BusquedaDePoisController buscarPoisPojos = new BusquedaDePoisController();
	    	//hago la busqueda y lo guardo en una lista para llenar la tabla en el frontend
	    	pojos = buscarPoisPojos.buscarPois(datos);	
	    }
	    
	    public void agregarInput(String dato){
	    	
	    	datos.add(dato);
	    	
	    }
}

