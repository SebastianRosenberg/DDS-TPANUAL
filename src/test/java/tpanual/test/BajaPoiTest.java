package tpanual.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import administrador.AdministradorDePoi;
import procesos.Proceso;
import procesos.ProcesosFactory;
import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Dias;
import tpanual.main.Direccion;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.poi.PuntoDeInteres;

public class BajaPoiTest {
	
	static PuntoDeInteres poi;
	static PuntoDeInteres poi2;
	static PuntoDeInteres poi3;
	
	@BeforeClass
	public static void setUp(){
		//Creo tres POI
		Direccion direccion= new Direccion.DireccionBuilder().barrio("Coghlan").callePrincipal("Av. Congreso").numero("3924").crearDireccion();
		ArrayList<String> palabrasClave = new ArrayList<String>();
		palabrasClave.add("Servicios");
		palabrasClave.add("Tarjeta de credito");
		List<Servicio> servicios=Servicio.getListaServicios("Depositos", "Pago de facturas");
		RubroFW rubro1=RubroFWFactory.getRubro("Muebleria", 700);
		HorarioDeAtencion horario=new HorarioDeAtencion();
		for (Dias dia:Dias.values()){ //Agrega el horario de atencion lunes a domingo de 9:00 a 14:00
			horario.addRangoDia(1700, 2030, dia);// y de 17:00 a 20:30
			horario.addRangoDia(900, 1400, dia);
		}
		poi = PuntoDeInteresFactory.getSucursal(-34.573001D, -58.490937D, "Banco Telecom BajaPoiTest", direccion, palabrasClave, servicios);
		poi2=PuntoDeInteresFactory.getParadaDeColectivo(600, 1200, "Parada de la linea 97 BajaPoiTest", direccion, palabrasClave, "97");
		poi3=PuntoDeInteresFactory.getLocalComercial(-50D, 3000D, "Correo Argentino BajaPoiTest", direccion, palabrasClave, rubro1, horario);
		
		//Los agrego
		AdministradorDePoi.getInstance().agregarPoi(poi);
		AdministradorDePoi.getInstance().agregarPoi(poi2);
		AdministradorDePoi.getInstance().agregarPoi(poi3);
	}
	
	@Test
	public void darDeBaja(){
		//Creo una lista con los POI que sé que antes agregué
		List<PuntoDeInteres> lista=new ArrayList<PuntoDeInteres>();
		lista.add(poi);
		lista.add(poi2);
		lista.add(poi3);
		
		//Genero y proceso
		Proceso proceso = ProcesosFactory.getBajaPoi(lista);
		proceso.procesar();
		
		//Obtengo los POI que di de baja buscandolos (uso la variable test en true para que no vaya a servicios externos)
		List<PuntoDeInteres> listaDadosDeBaja=AdministradorDePoi.getInstance().busquedaDePuntosDeInteres("Banco Telecom BajaPoiTest", true);
		listaDadosDeBaja.addAll(AdministradorDePoi.getInstance().busquedaDePuntosDeInteres("Parada de la linea 97 BajaPoiTest", true));
		listaDadosDeBaja.addAll(AdministradorDePoi.getInstance().busquedaDePuntosDeInteres("Correo Argentino BajaPoiTest", true));
		
		//Itero la lista para ver que efectivamente esten dados de baja
		Iterator<PuntoDeInteres> it=listaDadosDeBaja.iterator();
		while (it.hasNext()){
			assertTrue(it.next().isDadoDeBaja());
		}
	}
	
}
