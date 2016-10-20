package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class BusquedaDePoiBean {
	 private List<Item> items;

	    @PostConstruct
	    public void init() {
	        items = new ArrayList<Item>();
	    }

	    public void add() {
	        items.add(new Item());
	    }

	    public void remove(Item item) {
	        items.remove(item);
	    }

	    public void save() {
	       //buscarPoiService
	    	//armarlista
	    	
	    	System.out.println("items: " + items);
	    }

	    public List<Item> getItems() {
	        return items;
	    }
}

