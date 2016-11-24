package tpanual.rubro;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tpanual.main.HorarioDeAtencion;

public class RubroFWFactory {
	private static List<RubroFW> rubros=new ArrayList<RubroFW>();
	
	public static RubroFW getRubro(String nombre, int radioCercania){
		Iterator<RubroFW> it=rubros.iterator();
		while (it.hasNext()){
			RubroFW ocur=it.next();
			if (ocur.esIgual(nombre, radioCercania)){
				return ocur;
			}			
		}
		RubroFW nuevoRubro=new RubroConcreteFW(nombre, radioCercania);
		rubros.add(nuevoRubro);
		return nuevoRubro;
	}
}
