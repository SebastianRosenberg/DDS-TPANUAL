package tpanual.temporizador;

import java.util.List;

import java.time.Duration;
import java.time.Instant;

import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

//completar la parte de notificar cliente via mail

public class Temporizador {
	
	public Instant TiempoInicioBusqueda ()
	{
		Instant inicio = Instant.now();
		return inicio;
		
	}

	public boolean LapsoBusquedaMayor (Instant inicio)
	{		
	Instant fin = Instant.now();
	Duration duration = Duration.between(inicio, fin);	
	return (Constantes.TIEMPO_MAXIMO_CONSULTA.compareTo(duration)==1);
	}

	public void ChequeoLapso (Instant inicio, Usuario usuario)
	{
		if(LapsoBusquedaMayor (inicio))
		{
			//notificar cliente via mail
		}
	}
}

//Utilizar el primer metodo antes de empezar la busqueda, en cuanto finaliza, llamar al segundo dentro de un if, 
//si entra notificar al admin/loquesea
