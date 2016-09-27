package tpanual.main.gui;

public abstract class Mostrable {
	
	protected MetodoGrafico mg;
	
	protected Mostrable(MetodoGrafico mg){
		this.mg=mg;
	}
	
	public abstract void mostrar();
}
