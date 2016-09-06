package tpanual.utilitarios;

import java.time.Duration;

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
	
	//REVISAR LA CLASE DURATION Y CON QUE LO LLENO	A
	public static Duration TIEMPO_MAXIMO_CONSULTA = Duration.ofSeconds(10);; 
}
