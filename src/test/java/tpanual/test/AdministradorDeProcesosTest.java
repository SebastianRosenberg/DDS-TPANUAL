package tpanual.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import procesos.AdministradorDeProcesos;
import procesos.EnviarMail;
import procesos.Nada;
import procesos.Proceso;
import procesos.ProcesosFactory;
import procesos.ReintentarNVeces;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class AdministradorDeProcesosTest {
	
	static Proceso bajaPoi;
	static Proceso bajaPoiSinAgregarADisponible;
	static Usuario usr;
	
	@BeforeClass
	public static void setUp() throws Exception{
		
		//Banco Telecom BajaPoiTest
		//Parada de la linea 97 BajaPoiTest
		
	Direccion direccion1 = new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
	List<String> palabras1 = new ArrayList<String>();
	palabras1.add("CGP");
	List<Servicio> servicios1 = Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
	HorarioDeAtencion hda = new HorarioDeAtencion();
	hda.addRangoDia(9, 18, 1);
	hda.addRangoDia(9, 18, 2);
	hda.addRangoDia(9, 18, 3);
	hda.addRangoDia(9, 18, 4);
	hda.addRangoDia(9, 18, 5);
	servicios1.get(0).setHorario(hda);
	
	PuntoDeInteres pdi = PuntoDeInteresFactory.getCGP(1235, 9494, "Banco Telecom BajaPoiTest", direccion1, palabras1,
				servicios1, 25);
	
	Direccion direccion2 = new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545").barrio("Once").crearDireccion();
	List<String> palabras2 = new ArrayList<String>();
	palabras1.add("CGP");
	List<Servicio> servicios2 = Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
	HorarioDeAtencion hda2 = new HorarioDeAtencion();
	hda.addRangoDia(9, 18, 1);
	hda.addRangoDia(9, 18, 2);
	hda.addRangoDia(9, 18, 3);
	hda.addRangoDia(9, 18, 4);
	hda.addRangoDia(9, 18, 5);
	servicios1.get(0).setHorario(hda2);
	
	PuntoDeInteres pdi2 = PuntoDeInteresFactory.getCGP(1235, 9494, "Parada de la linea 97 BajaPoiTest", direccion2, palabras2,
				servicios2, 25);	
	
	GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	usr = gestor.crearTerminalActivo("juanete_tester");
	usr.agregarPoi(pdi);
	usr.agregarPoi(pdi2);
	
		
    bajaPoi = ProcesosFactory.getBajaPoi();
	 bajaPoiSinAgregarADisponible = ProcesosFactory.getBajaPoi();
	 //actLocalComercial = ProcesosFactory.getActualizacionLocalComercial();
	List<Proceso> listaProcesos = new ArrayList<Proceso>();
	listaProcesos.add(bajaPoi);
	Proceso multiple = ProcesosFactory.getProcesoMultipleComposite(listaProcesos);
	

	
	AdministradorDeProcesos.AgregarProcesoDisponible(multiple);
	AdministradorDeProcesos.AgregarProcesoDisponible(bajaPoi);
	//AdministradorDeProcesos.AgregarProcesoDisponible(actLocalComercial);
	
	
	AdministradorDeProcesos.EjecutarProceso(bajaPoi, usr, new ReintentarNVeces(2));
	//AdministradorDeProcesos.EjecutarProceso(actLocalComercial, usr, new EnviarMail());
	AdministradorDeProcesos.EjecutarProceso(multiple, usr, new Nada());

	}
	@Test
	public void EjecutaronDosProcesosTest() {
		assertTrue(AdministradorDeProcesos.GetProcesosEjecutados().size() == 2);
		}
	
	
	@Test (expected=Exception.class)
	public void NoSePuedeEjecutarUnProcesoQueNoEstaDisponibleTest() throws Exception{
		
		AdministradorDeProcesos.EjecutarProceso(bajaPoiSinAgregarADisponible, usr, new ReintentarNVeces(2));
		
	}
}
