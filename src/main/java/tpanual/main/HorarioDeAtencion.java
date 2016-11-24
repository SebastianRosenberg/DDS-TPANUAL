package tpanual.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;

@Entity
@Table(name = "HORARIO_DE_ATENCION")
public class HorarioDeAtencion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Intervalo> horarios;

	public HorarioDeAtencion() {
		horarios = new HashSet<Intervalo>();
	}

	public void addRangoDia(int desde, int hasta, int dia) {
		int horaDesde = desde / 100;
		int minutosDesde = desde % 100;
		int horaHasta = hasta / 100;
		int minutosHasta = hasta % 100;
		DateTime fechaDesde = new DateTime().withDayOfWeek(dia).withHourOfDay(horaDesde).withMinuteOfHour(minutosDesde);
		DateTime fechaHasta = new DateTime().withDayOfWeek(dia).withHourOfDay(horaHasta).withMinuteOfHour(minutosHasta);
		Intervalo intervalo = new Intervalo();
		intervalo.setIntervalo(new Interval(fechaDesde, fechaHasta));
		horarios.add(intervalo);
	}

	public boolean estaEnHorarioDeAtencion(int dia, int horario) {
		int hora = horario / 100;
		int minutos = horario % 100;
		boolean resultado = false;
		DateTime fecha = new DateTime().withDayOfWeek(dia).withHourOfDay(hora).withMinuteOfHour(minutos);
		Iterator<Intervalo> intervalos = horarios.iterator();

		while (intervalos.hasNext() && !resultado) {
			Intervalo inter = intervalos.next();
			DateTime fechaAux = fecha.withWeekOfWeekyear(inter.getIntervalo().getStart().getWeekOfWeekyear());
			resultado = inter.getIntervalo().contains(fechaAux);
		}
		return resultado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String retorno = "";
		for (Intervalo unHorario : horarios) {
			String aux = unHorario.toString();
			retorno += aux + ".<br/>";
		}

		return retorno;
	}
}
