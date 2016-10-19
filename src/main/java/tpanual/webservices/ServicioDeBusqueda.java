package tpanual.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import administrador.AdministradorDePoi;
import tpanual.webservices.recursos.PoiPojo;
import tpanual.webservices.recursos.TraductorDePojo;

@Path("/BusquedaPois")
public class ServicioDeBusqueda {

	   @GET
	   @Path("/pois")
	   @Produces(MediaType.APPLICATION_XML)
	   public List<PoiPojo> getPois(){
		   TraductorDePojo tdp = new TraductorDePojo();
	      //return tdp.traducirPoisAPojos(AdministradorDePoi.getInstance().busquedaDePuntosDeInteres("hola", true));
		   List<PoiPojo> l =  new ArrayList<PoiPojo>();
		   l.add(new PoiPojo(32));
		   return l;
	   }	
	
}
