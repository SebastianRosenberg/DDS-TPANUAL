package tpanual.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//import org.junit.BeforeClass;
import org.junit.Test;

//import administrador.AdministradorDePoi;
import procesos.actualizacionLC.ActualizacionLocalComercial;
import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.HorarioDeAtencion;
//import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;
//import tpanual.utilitarios.DatosActualizacion;
import tpanual.utilitarios.ParseadorDeTextos;


public class ParseadorDeTextosTest {

	//public ParseadorDeTextosTest() {
		// TODO Auto-generated constructor stub
//	}
	
	@Test
	public void parseadorDeTextoTest() throws IOException{
		
		Collection<ActualizacionLocalComercial> listaDeComercioTest = new ArrayList<ActualizacionLocalComercial>();
		//List<DatosActualizacion> listaDeComercioTest = new ArrayList<DatosActualizacion>();
		List<ActualizacionLocalComercial> listaDeComerciosAActualizar = new ArrayList<ActualizacionLocalComercial>();
		
		//Set up
		//List<String> palabrasClaves = new ArrayList<> ( Arrays.asList ( "escolar" , "uniforme" , "moda" ));
		List<String> palabrasClaves = new ArrayList<String>();
		palabrasClaves.add("escolar");
		palabrasClaves.add("uniforme");
		palabrasClaves.add("moda");
        Direccion direccion= new Direccion.DireccionBuilder().crearDireccion();
		ArrayList<String> palabrasC = new ArrayList<String>();
		HorarioDeAtencion horario=new HorarioDeAtencion();
		RubroFW rubro =RubroFWFactory.getRubro("", 0);
        PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getLocalComercial(0, 0, "Carrousel", direccion, palabrasC,rubro,horario);
        
		ActualizacionLocalComercial datos1 = new ActualizacionLocalComercial(puntoFactory,palabrasClaves);
		listaDeComercioTest.add(datos1);
		
		//Creo una instancia de la clase ParseadorDeTextos
		//ParseadorDeTextos parser = new ParseadorDeTextos();
		
		//Llamo al metodo de la clase ParseadorDeTextos que se encarga de devolver los objetos con los datos para actualizar.
		listaDeComerciosAActualizar = ParseadorDeTextos.parsearTexto();
		
		//listaDeComerciosAActualizar.size();
		
		assertTrue(listaDeComerciosAActualizar.contains(datos1));
		
		
		//un caso que no existe en la lista
		List<String> palabrasClaves2 = new ArrayList<String>();
		palabrasClaves.add("El tercer grande");
		//ActualizacionLocalComercial datos2 = new ActualizacionLocalComercial("CARC",palabrasClaves2);
		
		//assertFalse(listaDeComerciosAActualizar.contains(datos2));
		
		//assertTrue(listaDeComercioTest.== listaDeComerciosAActualizar.size());
		//listaDeComercioTest.removeAll(listaDeComerciosAActualizar);
		//assertTrue(listaDeComercioTest.isEmpty());
		//assertTrue(listaDeComercioTest.containsAll(listaDeComerciosAActualizar));
		//assertTrue(listaDeComercioTest = listaDeComerciosAActualizar );
		//assertTrue(listaDeComercioTest.contains("Carrousel").);
		//assertEquals(listaDeComercioTest,listaDeComerciosAActualizar);
		//assertSame(listaDeComercioTest, listaDeComerciosAActualizar);
		//assertArrayEquals(listaDeComercioTest.toArray(), listaDeComerciosAActualizar.toArray());
	}
	
	
}
