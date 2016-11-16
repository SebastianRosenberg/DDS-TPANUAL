package tpanual.utilitarios;

import java.util.Iterator;
import java.util.List;

import tpanual.main.poi.PalabraClave;
import tpanual.main.poi.PuntoDeInteres;

public class Utilitarios {
	
	private static HibernateFactorySessions hfs;
	
	public static boolean buscarPalabraEnUnaLista(String x, List<String> lista){
		Iterator<String> it=lista.iterator();
		boolean aparicion=false;
		while (it.hasNext() && !aparicion){
			if (it.next().toUpperCase().indexOf(x.toUpperCase()) != -1) 
				aparicion=true;
		}
		return aparicion;
	}
	
	public static boolean buscarPalabraEnPalabrasClave(String x, List<PalabraClave> lista){
		Iterator<PalabraClave> it=lista.iterator();
		boolean aparicion=false;
		while (it.hasNext() && !aparicion){
			PalabraClave pc = it.next();
			if (pc.getNombre().toUpperCase().indexOf(x.toUpperCase()) != -1) 
				aparicion=true;
		}
		return aparicion;
	}
	
	public static List<PuntoDeInteres> fusionarListasSinRepetidos(List<PuntoDeInteres> lista1, List<PuntoDeInteres> lista2 ){
		if (lista1==null && lista2!=null)
			return lista2;
		if (lista1!=null && lista2==null)
			return lista1;
		if (lista1==null && lista2==null)
			return null;
		Iterator<PuntoDeInteres> i= lista1.iterator();
		while (i.hasNext()){
			PuntoDeInteres p=i.next();
			if (!lista2.contains(p))
				lista2.add(p);
		}
		return lista2;
	}
	
	public static HibernateFactorySessions getHibernateFactorySessions(){
		if (hfs==null)
			hfs = new HibernateFactorySessions();
		return hfs;
	}
	
}
