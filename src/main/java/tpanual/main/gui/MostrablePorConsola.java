package tpanual.main.gui;

public class MostrablePorConsola implements MetodoGrafico {

	private static MostrablePorConsola instance;
	
	private MostrablePorConsola (){
		
	}
	
	public static MostrablePorConsola getInstance(){
		if (instance == null)
			instance=new MostrablePorConsola();
		return instance;
	}
	
	
	@Override
	public void mostrar(Object mostrable) {
		System.out.println(mostrable.toString());
	}

}
