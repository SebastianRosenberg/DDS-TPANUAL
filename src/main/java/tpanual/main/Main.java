package tpanual.main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Entity;

import administrador.AdministradorDePoi;
import administrador.Busqueda;
import procesos.bajaDePois.ParserDeBajas;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;
import tpanual.main.direccion.Pais;
import tpanual.main.direccion.Provincia;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.mongo.MongoDBConnection;
import tpanual.rubro.RubroFWFactory;
import tpanual.seguridad.GestorDeUsuarios;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.HibernateFactorySessions;

public class Main {
	public static void main(String[] args) {
		HibernateFactorySessions hs;
		AdministradorDePoi adp = new AdministradorDePoi();
		/*
		 * En esta clase se van a preparar todas las cargas iniciales para
		 * utilizar el sistema. Se van a crear Usuarios (administradores y
		 * terminal) Puntos de interés Búsquedas
		 * 
		 */

		/* Set up Usuarios */
		// administradores
		hs = new HibernateFactorySessions();
		GestorDeUsuarios gestor = GestorDeUsuarios.getInstance();
		
		Pais pais = new Pais("Argentina");
		Provincia provincia = new Provincia("Buenos Aires", pais);
		Localidad unaLocalidad = new Localidad("San Martin", 1712, provincia);

		// Usuario nuevoUsuarioAdmin =gestor.crearAdministrador("Admin1",
		// "mailsebas@hotmail.com","1Admin");
		// Usuario nuevoUsuarioAdmin2 =gestor.crearAdministrador("Admin2",
		// "mailsebas@hotmail.com","2Admin");
		Usuario admin1=gestor.crearAdministrador("Admin1", "mailAdmin1@hotmail.com", "1Admin");
		gestor.crearAdministrador("Admin2", "mailAdmin2@hotmail.com", "2Admin");
		// terminales
		// Usuario nuevoUsuarioTerminal =
		// gestor.crearTerminalActivo("terminal1");
		// Usuario nuevoUsuarioTerminal2 =
		// gestor.crearTerminalActivo("terminal2");
		gestor.crearTerminalActivo("terminal");
		gestor.crearTerminalActivo("terminal2");

		/* Set up Puntos de interés */

		Direccion direccion1 = new Direccion.DireccionBuilder().callePrincipal("Pueyrredon").numero("545")
				.barrio("Once").crearDireccion();
		Direccion direccion2 = new Direccion.DireccionBuilder().callePrincipal("Mitre").numero("1354")
				.barrio("Congreso").crearDireccion();
		Direccion direccion3 = new Direccion.DireccionBuilder().callePrincipal("Paseo Colón").numero("745")
				.barrio("San Telmo").entreCalle1("Chile").entreCalle2("Av. Independencia").localidad(unaLocalidad)
				.crearDireccion();
		Direccion direccion4 = new Direccion.DireccionBuilder().callePrincipal("Av. Congreso").numero("5205")
				.barrio("Villa Urquiza").entreCalle1("Triunvirato").entreCalle2("Un pasaje").crearDireccion();
		

		List<String> palabras1 = new ArrayList<String>();
		palabras1.add("CGP");
		palabras1.add("Zona peligrosa");

		List<String> palabras2 = new ArrayList<String>();
		palabras2.add("Zona Segura");

		List<String> palabras3 = new ArrayList<String>();
		palabras3.add("Buena Atención");
		palabras3.add("Rapidez");

		List<String> palabras4 = new ArrayList<String>();
		palabras4.add("Buena ubicación");

		List<Servicio> servicios1 = Servicio.getListaServicios("Registro Civil", "Denuncias", "Pensiones");
		List<Servicio> servicios2 = Servicio.getListaServicios("Jubilaciones");
		List<Servicio> servicios3 = Servicio.getListaServicios("Cheques", "Pagos", "Cobros", "Cajeros");

		servicios1.get(0).setHorario(getHorario1());
		servicios1.get(1).setHorario(getHorario2());
		servicios1.get(2).setHorario(getHorario3());
		servicios2.get(0).setHorario(getHorario3());
		servicios3.get(0).setHorario(getHorario3());
		
		HorarioDeAtencion hda = new HorarioDeAtencion();
		hda.addRangoDia(9, 18, 1);
		hda.addRangoDia(9, 18, 2);
		hda.addRangoDia(9, 18, 3);
		hda.addRangoDia(9, 18, 4);
		hda.addRangoDia(9, 18, 5);

		// Persisto el punto de interes
		PuntoDeInteres pdi = PuntoDeInteresFactory.getCGP(1235, 9494, "GCP Comuna 1", direccion1, palabras1,
				servicios1, 25);
		PuntoDeInteres pdi2 = PuntoDeInteresFactory.getCGP(2681, 3221D, "GCP Comuna 2", direccion2, palabras2,
				servicios2, 25);
		PuntoDeInteres pdi5 = PuntoDeInteresFactory.getLocalComercial(2135, 9465, "Local de ropa", direccion4, palabras4, 
				RubroFWFactory.getRubro("Ropa", 30), hda);
		
		
		
		PuntoDeInteres pdi3 = PuntoDeInteresFactory.getSucursal(300D, 185D, "Banco Galicia", direccion3, palabras3,
				servicios3);
		PuntoDeInteres pdi4 = PuntoDeInteresFactory.getParadaDeColectivo(-34.570584, -58.491547, "Parada Linea 169",
				direccion4, palabras4, "169");
		
		// idPoiModificado = pdi.getId();
		// idPoiEliminado = pdi2.getId();
		// hs = new HibernateFactorySessions();
		hs.add(pdi);
		hs.add(pdi2);
		hs.add(pdi3);
		hs.add(pdi4);
		hs.add(pdi5);

		//hay que agregar casos para locales y mas paradas de colectivos y las busquedas
		
		List<PuntoDeInteres> l = admin1.busquedaDePuntosDeInteres("gcp", false);
		Iterator<PuntoDeInteres> it = l.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		
	}

	private static HorarioDeAtencion getHorario1() {
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia = 1; dia < 6; dia++) {
			horario.addRangoDia(800, 1700, dia);
		}
		return horario;
	}

	private static HorarioDeAtencion getHorario2() {
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia = 1; dia < 7; dia++) {
			horario.addRangoDia(1000, 1600, dia);
		}
		return horario;
	}

	private static HorarioDeAtencion getHorario3() {
		HorarioDeAtencion horario = new HorarioDeAtencion();
		for (int dia = 2; dia < 6; dia++) {
			horario.addRangoDia(1200, 2000, dia);
		}
		return horario;
	}

		
	
}
