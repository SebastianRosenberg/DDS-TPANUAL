package tpanual.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import tpanual.main.HorarioDeAtencion;
import tpanual.main.IComunicacionServiciosExternos;
import tpanual.main.Servicio;
import tpanual.main.direccion.Direccion;
import tpanual.main.poi.CGP;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.main.poi.SucursalBanco;

public class ComunicacionServiciosExternos implements
		IComunicacionServiciosExternos {


	public List<PuntoDeInteres> obtenerCGPEnCalleOZona(String calleOZona) {
		try {
			Client client = Client.create();
			String strUrl = String.format("http://trimatek.org/Consultas/centro?zona=%s", "Boedo");
			WebResource webResource = client.resource(strUrl);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
			String s = response.getEntity(String.class);
			JsonParser parser = new JsonParser();
			
			JsonArray jsonArray = (JsonArray)parser.parse(s);
			
			
			List<PuntoDeInteres> listaPois = new ArrayList<PuntoDeInteres>();
			
			for(JsonElement unPoiJsonElement : jsonArray){
				
				JsonObject unPoiJsonObject = unPoiJsonElement.getAsJsonObject();
				listaPois.add(GenerarPoiAPartirDeDatosExternos(unPoiJsonObject));
			}

			return listaPois;
		} 
		catch (Exception e) 
		{
			return null;
		}

	}

	private PuntoDeInteres GenerarPoiAPartirDeDatosExternos(JsonObject unPoiJsonObject) {
		CGP tipoPoi = new CGP(
						getServiciosCGP(unPoiJsonObject.get("servicios").getAsJsonArray()),
						unPoiJsonObject.get("comuna").getAsInt());
		
		Direccion direccion = parsearDireccion(unPoiJsonObject.get("direccion").getAsString());
		PuntoDeInteres poi = 
				new PuntoDeInteres(
						0,
						0,
						"",
						direccion,
						new ArrayList<String>(),
						tipoPoi);
		
		return poi;
	}

	private Direccion parsearDireccion(String direccionSerealizada) {
		// TODO ESTO NO FUNCIONA CON EL ULTIMO PARAMETRO QUE DEBE SER DE TIPO LOCALIDAD
		return new Direccion("","","","","","","","","",null);
	}

	private List<Servicio> getServiciosCGP(JsonArray serviciosJsonArray) {
		List<Servicio> listServicios = new ArrayList<Servicio>();
		
		for(JsonElement unServicioJsonElement : serviciosJsonArray){
			JsonObject unServicioJsonObject = unServicioJsonElement.getAsJsonObject();
			
			Servicio servicio = new Servicio(
					unServicioJsonObject.get("nombre").getAsString());
			
			HorarioDeAtencion horario = getHorariosServicio(unServicioJsonObject.get("horarios").getAsJsonArray());
				
			servicio.setHorario(horario);
			
			listServicios.add(servicio);
		}
		return listServicios;
	}

	private HorarioDeAtencion getHorariosServicio(JsonArray asJsonArray) {
		HorarioDeAtencion nuevoHorario = new HorarioDeAtencion();
		
		for (JsonElement unHorarioJsonElement: asJsonArray){
			JsonObject unHorarioJsonObject = unHorarioJsonElement.getAsJsonObject();
			
			int dia = unHorarioJsonObject.get("diaSemana").getAsInt();
			int horaDesde = unHorarioJsonObject.get("horaDesde").getAsInt()*100 + unHorarioJsonObject.get("minutosDesde").getAsInt();
			int horaHasta = unHorarioJsonObject.get("horaHasta").getAsInt()*100 + unHorarioJsonObject.get("minutosHasta").getAsInt();
			
			switch (dia) {
			case 0:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.DOMINGO);
				break;
			case 1:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.LUNES);
				break;
			case 2:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.MARTES);
				break;
			case 3:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.MIERCOLES);
				break;
			case 4:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.JUEVES);
				break;
			case 5:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.VIERNES);
				break;
			case 6:
				nuevoHorario.addRangoDia(horaDesde, horaHasta, Dias.SABADO);
				break;
			}
		}
		return nuevoHorario;
	}

	@Override
	public List<PuntoDeInteres> obtenerBancosPorNombreYServicio(
			String nombreBanco, String nombreServicioOfrecido) {
		try{
			Client client = Client.create();
			String strUrl = String.format("http://trimatek.org/Consultas/banco?banco=%s&servicio=%s", "Santander", "Pagos");
			WebResource webResource = client.resource(strUrl);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
			String s = response.getEntity(String.class);
			JsonParser parser = new JsonParser();
			
			JsonArray jsonArray = (JsonArray)parser.parse(s);
			
			List<PuntoDeInteres> listaPois = new ArrayList<PuntoDeInteres>();
			
			for(JsonElement unPoiJsonElement : jsonArray){
				
				JsonObject unPoiJsonObject = unPoiJsonElement.getAsJsonObject();
				listaPois.add(GenerarPoiBancoAPartirDeDatosExternos(unPoiJsonObject));
			}
			return listaPois;
			
		}
		catch(Exception e){
			return null;
		}
		
	}

	private PuntoDeInteres GenerarPoiBancoAPartirDeDatosExternos(
			JsonObject unPoiJsonObject) {
		

		List<Servicio> servicios = GetServiciosBanco(unPoiJsonObject.get("servicios").getAsJsonArray());
		SucursalBanco tipoPoi = new SucursalBanco(servicios);
		
		PuntoDeInteres poi = 
				new PuntoDeInteres(
						0,
						0,
						"",
						parsearDireccion(""),
						new ArrayList<String>(),
						tipoPoi);
		
		return poi;
		
	}

	private List<Servicio> GetServiciosBanco(JsonArray jsonArray) {
		List<Servicio> servicios = new ArrayList<Servicio>();
	
		for(JsonElement unServicioJsonElement : jsonArray){
			
			JsonObject unServicioJsonObject = unServicioJsonElement.getAsJsonObject();
			String nombreServicio = unServicioJsonObject.getAsString();
			Servicio unServicio = new Servicio(nombreServicio);
			servicios.add(unServicio);
		}
		return servicios;
	}

}
