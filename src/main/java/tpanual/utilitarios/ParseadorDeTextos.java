package tpanual.utilitarios;

import java.io.*;
import java.util.*;

import procesos.actualizacionLC.ActualizacionLocalComercial;
import tpanual.Rubro.RubroFW;
import tpanual.Rubro.RubroFWFactory;
import tpanual.factory.PuntoDeInteresFactory;
import tpanual.main.Direccion;
import tpanual.main.HorarioDeAtencion;
import tpanual.main.poi.PuntoDeInteres;

public class ParseadorDeTextos {

	//List<String> palabrasClaves = new ArrayList<String>();
	//public ParseadorDeTextos() {
		// TODO Auto-generated constructor stub
	//}
private static BufferedReader abrirBuffer() throws FileNotFoundException{
	
	//Get file from resources folder
	FileReader fr = new FileReader("src/main/resources/file/actualizacionLocales.txt");
    BufferedReader bufferLectura = new BufferedReader(fr);
    //BufferedReader bufferLectura = new BufferedReader (new FileReader ("C:/Users/rosa/Documents/GitHub/DDS-TPANUAL/src/main/resources/file/actualizacionLocales.txt"));//file.getPath()));//Constantes.RUTA_ARCHIVO_ACTUALIZACION_LOCALES_COMERCIALES));
	 return bufferLectura;
}


private static void cerrarBuffer(BufferedReader buffer) throws IOException{
	
	buffer.close();
	
}

public static List<ActualizacionLocalComercial> parsearTexto() throws IOException{
	String nombre = null;
	String linea = null;
	//List<String> palabrasClaves = new ArrayList<String>();
	BufferedReader bufferDatos;
	List<ActualizacionLocalComercial> comerciosConActualizacion = new ArrayList<ActualizacionLocalComercial>();
	
	bufferDatos = abrirBuffer();
	
	//linea = bufferDatos.readLine(); 
	while (bufferDatos.ready()){
		linea = bufferDatos.readLine();
		StringTokenizer token = new StringTokenizer (linea);
		List<String> palabrasClaves = new ArrayList<String>();
		
		//Tengo que quitar el ; del final de cada nombre de fantasía.
		nombre = token.nextToken();

		// bucle por todas las palabras
        while (token.hasMoreTokens()){
        	palabrasClaves.add(token.nextToken());
        }
		
		//DatosActualizacion datos = new DatosActualizacion(nombre, palabrasClaves);
        Direccion direccion= new Direccion.DireccionBuilder().crearDireccion();
		ArrayList<String> palabrasC = new ArrayList<String>();
		HorarioDeAtencion horario=new HorarioDeAtencion();
		RubroFW rubro =RubroFWFactory.getRubro("", 0);
        PuntoDeInteres puntoFactory = PuntoDeInteresFactory.getLocalComercial(0, 0, nombre.substring(0, nombre.length()-1), direccion, palabrasC,rubro,horario);
        
        //palabrasClaves.addAll(palabrasClavesporLinea);
        ActualizacionLocalComercial datos = new ActualizacionLocalComercial(puntoFactory, palabrasClaves);
        
        comerciosConActualizacion.add(datos);
		
		//limpio las variables para los datos de la próxima línea
		nombre = null;
	}
	
	cerrarBuffer(bufferDatos);
	
	return comerciosConActualizacion;
	
}

}
