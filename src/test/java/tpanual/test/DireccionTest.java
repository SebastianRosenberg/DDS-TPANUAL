package tpanual.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tpanual.main.direccion.Direccion;
import tpanual.main.direccion.Localidad;

public class DireccionTest {
	@Test
	public void creacionTest(){
		Direccion d=new Direccion.DireccionBuilder().barrio("barrio").callePrincipal("callePrincipal").codigoPostal("codigoPostal").departamento("departamento")
			.entreCalle1("entreCalle1").entreCalle2("entreCalle2").numero("numero").localidad(Localidad.getLocalidadAuxiliar())
				.piso("piso").unidad("unidad").crearDireccion();
		
		assertTrue(d.toString().equals("callePrincipal entreCalle1 entreCalle2 numero piso departamento unidad barrio Castelar"));

	}
}
