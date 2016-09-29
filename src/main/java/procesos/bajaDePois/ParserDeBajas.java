package procesos.bajaDePois;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.utilitarios.Constantes;

public class ParserDeBajas {
	
	public static String rootElement="bajas";
	public static String busqueda="busqueda";
	public static String fechaDeBaja="fechaDeBaja";
	
	public static List<SolicitudBajaJson> obtenerBajas() throws FileNotFoundException, ArchivoBajasException{
		JsonParser parser = new JsonParser();
		FileReader fr = new FileReader(Constantes.UBICACION_ARCHIVO_BAJAS);
		JsonElement datos = parser.parse(fr);
		return parsearObjeto(datos);
			
	}
	
	public static List<SolicitudBajaJson> parsearObjeto(JsonElement elemento) throws ArchivoBajasException{
		JsonElement principal=validarRaiz(elemento);
		List<SolicitudBajaJson> lista=new ArrayList<SolicitudBajaJson>();
		
		if (principal.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            Iterator<JsonElement> i = array.iterator();
            while (i.hasNext()) {
                JsonElement e = i.next();
                lista.add(crearPuntoDeInteres(e));
            }
            return lista;
		}else{
			throw new ArchivoBajasException("El elemento principal no es un array");
		}
	}
	public static JsonElement validarRaiz(JsonElement elemento) throws ArchivoBajasException{
		if (elemento.isJsonObject()){
			Iterator<Entry<String, JsonElement>> l=elemento.getAsJsonObject().entrySet().iterator();
			Entry<String, JsonElement> entrada=l.next();
            
			if (entrada.getKey().equals(rootElement)){
				return entrada.getValue();
			}else{				
				throw new ArchivoBajasException("El elemento principal no se llama " + rootElement);	
			}
		}else{
			throw new ArchivoBajasException("El elemento principal no es un objeto Json valido");
		}
	}

	public static SolicitudBajaJson crearPuntoDeInteres(JsonElement elemento) throws ArchivoBajasException{
		if (elemento.isJsonObject()){
			Iterator<Entry<String, JsonElement>> l=elemento.getAsJsonObject().entrySet().iterator();
			Entry<String, JsonElement> busqueda=l.next();
			Entry<String, JsonElement> fecha=l.next();
            
			if (busqueda.getKey().equals(busqueda) && fecha.getKey().equals(fechaDeBaja)){
				return new SolicitudBajaJson(busqueda.getValue().getAsJsonPrimitive().getAsString(), fecha.getValue().getAsJsonPrimitive().getAsLong());
			}else{
				throw new ArchivoBajasException("Elemento no valido: " + busqueda.getKey() + " - " + fecha.getKey());	
			}
		}else{
			throw new ArchivoBajasException("El elemento del array no es un objeto Json valido");
		}
	}	
}
