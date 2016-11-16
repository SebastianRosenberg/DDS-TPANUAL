package tpanual.temporizador;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.Instant;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

import tpanual.utilitarios.Email;



public class Temporizador {
	
	private Instant inicio;
	
	public void tiempoInicioBusqueda (){
		inicio = Instant.now();
		
	}

	public Duration ChequeoLapso (Usuario usuario){
		
		Duration total = Duration.millis(Instant.now().getMillis() - inicio.getMillis());
		
		if(Constantes.TIEMPO_MAXIMO_CONSULTA.getMillis() < total.getMillis()){
			usuario.notificar();
		}
		return total;
	}
}
