package tpanual.reportes;

import java.util.Iterator;
import java.util.List;

import tpanual.main.gui.MetodoGrafico;
import tpanual.main.gui.Mostrable;

public class Reporte<T extends CriterioReporte> extends Mostrable {
	private List<T> lista;
	
	
	public Reporte(List<T> l, MetodoGrafico mg){
		super(mg);
		lista=l;
	}

	public List<T> getLista() {
		return lista;
	}

	@Override
	public void mostrar() {
		Iterator<T> it=lista.iterator();
		while(it.hasNext()){
			mg.mostrar(it.next());
		}
	}
}
