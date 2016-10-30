package tpanual.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class HorarioDeAtencion {
	
	private List<Interval> horarios = new ArrayList<Interval>();

	
	public void addRangoDia (int desde, int hasta, int dia) {
		int horaDesde = desde/100;
		int minutosDesde = desde%100;
		int horaHasta = hasta/100;
		int minutosHasta = hasta%100;
		DateTime fechaDesde = new DateTime().withDayOfWeek(dia).withHourOfDay(horaDesde).withMinuteOfHour(minutosDesde);
		DateTime fechaHasta = new DateTime().withDayOfWeek(dia).withHourOfDay(horaHasta).withMinuteOfHour(minutosHasta);
		Interval intervalo = new Interval (fechaDesde, fechaHasta);
		horarios.add(intervalo);
	}
	
	public boolean estaEnHorarioDeAtencion(int dia, int horario){
		int hora = horario/100;
		int minutos = horario%100;
		boolean resultado = false;
		DateTime fecha = new DateTime().withDayOfWeek(dia).withHourOfDay(hora).withMinuteOfHour(minutos);
		Iterator<Interval> intervalos = horarios.iterator();
		
		while (intervalos.hasNext()&& !resultado){
			Interval inter = intervalos.next();
			DateTime fechaAux = fecha.withWeekOfWeekyear(inter.getStart().getWeekOfWeekyear());
			resultado = inter.contains(fechaAux);
		}
		return resultado;
	}
	
}
