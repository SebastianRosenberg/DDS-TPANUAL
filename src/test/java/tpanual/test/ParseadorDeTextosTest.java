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

	@Test
	public void parseadorDeTextoTest() throws IOException{
		
		Collection<ActualizacionLocalComercial> listaDeComercioTest = new ArrayList<ActualizacionLocalComercial>();
		List<ActualizacionLocalComercial> listaDeComerciosAActualizar = new ArrayList<ActualizacionLocalComercial>();
		
		//Set up
		//Un caso que se va a encontrar tanto en la lista de Poi como en el archivo
		//de texto.
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
		
		//Llamo al metodo de la clase ParseadorDeTextos que se encarga de devolver los objetos con los datos para actualizar.
		listaDeComerciosAActualizar = ParseadorDeTextos.parsearTexto();
		
		assertTrue(listaDeComerciosAActualizar.contains(datos1));
		
		//un caso que no existe en la lista de comercios a Actualizar
		List<String> palabrasClaves2 = new ArrayList<String>();
		palabrasClaves2.add("Papeleria comercial");
		Direccion direccion2= new Direccion.DireccionBuilder().crearDireccion();
		ArrayList<String> palabrasC2 = new ArrayList<String>();
		HorarioDeAtencion horario2=new HorarioDeAtencion();
		RubroFW rubro2 =RubroFWFactory.getRubro("", 0);
        PuntoDeInteres puntoFactory2 = PuntoDeInteresFactory.getLocalComercial(0, 0, "Papeleria Juan", direccion2, palabrasC2,rubro2,horario2);
        
		ActualizacionLocalComercial datos2 = new ActualizacionLocalComercial(puntoFactory2,palabrasClaves2);
		
		assertFalse(listaDeComerciosAActualizar.contains(datos2));		
	}
	
	
}
