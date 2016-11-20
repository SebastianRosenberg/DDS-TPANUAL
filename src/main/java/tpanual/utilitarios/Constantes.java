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
	
	public static final String QUERY_CGP = "select pdi.* from punto_de_interes pdi, poi_cgp cgp, cgp_servicios cgps, servicio sv, poi_palabras pp , palabra_clave pc where pdi.nombre like :x and pdi.TIPO_PUNTO_INTERES_ID = cgp.ID and cgp.COMUNA_ID like :x and cgps.CGP_ID = cgp.ID and cgps.SERVICIO_ID = sv.ID and sv.NOMBRE like :x and pp.POI_ID = pdi.ID and pp.PALABRA_CLAVE_ID=pc.ID and pc.NOMBRE like :x";

	public static final String QUERY_LOCAL_COMERCIAL = "from punto_de_interes pdi, poi_local_comercial lc, rubro rubro, poi_palabras pp, palabra_clave pc where pdi.nombre like :x and pdi.TIPO_PUNTO_INTERES_ID = lc.ID and lc.RUBRO_ID=rubro.ID and rubro.NOMBRE like :x and pp.POI_ID = pdi.ID and pp.PALABRA_CLAVE_ID = pc.id and pc.nombre like :x";

	public static final String QUERY_PARADA_COLECTIVO = "from punto_de_interes pdi, poi_parada_de_colectivo col, poi_palabras pp, palabra_clave pc where pdi.nombre like :x and pdi.TIPO_PUNTO_INTERES_ID = col.ID and col.LINEA like :x and pp.POI_ID = pdi.ID and pp.PALABRA_CLAVE_ID = pc.id and pc.nombre like :x";

	public static final String QUERY_SUCURSAL_BANCO = "from punto_de_interes pdi, poi_sucursal_banco sb, sucursal_servicios ss, servicio sv, poi_palabras pp, palabra_clave pc where pdi.nombre like :x and pdi.TIPO_PUNTO_INTERES_ID = sb.ID and ss.SUCURSAL_ID = sb.ID and ss.SERVICIO_ID = sv.ID and sv.NOMBRE like :x and pp.POI_ID = pdi.ID and pp.PALABRA_CLAVE_ID = pc.id and pc.nombre like :x";
}
