package tpanual.temporizador;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.Instant;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

import tpanual.utilitarios.Email;



public class Temporizador {
	
	public static Instant TiempoInicioBusqueda ()
	{
		Instant inicio = Instant.now();
		return inicio;
		
	}

	public static Duration LapsoBusqueda (Instant inicio)
	{		
	Instant fin = Instant.now();
	return Duration.millis(fin.getMillis() - inicio.getMillis());
	
	}

	public static void ChequeoLapso (Duration total, Usuario usuario)
	{
		if(Constantes.TIEMPO_MAXIMO_CONSULTA.getMillis() < total.getMillis())
		{
			Email EnviadorMail = new Email(usuario.getEmail(),
	                "Aviso de tardanza en busqueda", "Este es un mensaje para notificar que una busqueda tardó mas del tiempo máximo.");
		}
	}
}

//Utilizar el primer metodo antes de empezar la busqueda, en cuanto finaliza, llamar al segundo dentro de un if, 
//si entra notificar al admin/loquesea
