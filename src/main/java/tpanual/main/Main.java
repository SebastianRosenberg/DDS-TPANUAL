package tpanual.main;

import java.io.FileNotFoundException;

import org.joda.time.DateTime;

import procesos.bajaDePois.ParserDeBajas;
import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;

public class Main {
	public static void main(String[] args) {
		Direccion d=new Direccion.DireccionBuilder().barrio("barrio").callePrincipal("callePrincipal").departamento("departamento")
				.entreCalle1("entreCalle1").entreCalle2("entreCalle2").numero("numero").localidad(Localidad.getLocalidadAuxiliar())
					.piso("piso").unidad("unidad").crearDireccion();
		System.out.println(d);
	}
}
