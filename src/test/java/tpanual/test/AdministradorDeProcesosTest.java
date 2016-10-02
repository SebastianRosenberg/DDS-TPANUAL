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
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;

public class AdministradorDeProcesosTest {
	
	static Proceso bajaPoi;
	static Proceso bajaPoiSinAgregarADisponible;
	static Usuario usr;
	
	@BeforeClass
	public static void setUp() throws Exception{
    bajaPoi = ProcesosFactory.getBajaPoi();
	 bajaPoiSinAgregarADisponible = ProcesosFactory.getBajaPoi();
	 //actLocalComercial = ProcesosFactory.getActualizacionLocalComercial();
	List<Proceso> listaProcesos = new ArrayList<Proceso>();
	listaProcesos.add(bajaPoi);
	Proceso multiple = ProcesosFactory.getProcesoMultipleComposite(listaProcesos);
	
	GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
	usr = gestor.crearTerminalActivo("pedritoTester");
	
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
