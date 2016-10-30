package tpanual.utilitarios;


import java.net.URL;

import org.joda.time.Duration;



public class Constantes {
	public static final int PARADA_DE_COLECTIVO_RADIO_DE_CERCANIA=100;
	public static final int SUCURSAL_BANCO_RADIO_DE_CERCANIA=500;
	public static final int CGP_CERCANIA=0;
	
	public static final boolean SUCURSAL_BANCO_DISPONIBILIDAD=false;
	public static final boolean PARADA_DE_COLECTIVO_DISPONIBILIDAD=true;
	public static final boolean LOCAL_COMERCIAL_DISPONIBILIDAD=false;
	public static final boolean CGP_COMERCIAL_DISPONIBILIDAD=false;
	
	public static final boolean SUCURSAL_BANCO_COINCIDENCIA=false;
	
	public static final int INTERVALO_DEHORAS_CONSIDERA_BUSQUEDA_RECIENTE=72;
	
	public static final int LUNES = 1;
	public static final int MARTES = 2;
	public static final int MIERCOLES = 3;
	public static final int JUEVES = 4;
	public static final int VIERNES = 5;
	public static final int SABADO = 6;
	public static final int DOMINGO = 7;

	public static Duration TIEMPO_MAXIMO_CONSULTA = Duration.millis(5000);
	
	//public static final String UBICACION_ARCHIVO_BAJAS = "C:\\Users\\dipatata\\Desktop\\jsonArchive.json";
	public static final String UBICACION_ARCHIVO_BAJAS = "src/main/resources/file/jsonArchive.json";
}
