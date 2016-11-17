package administrador;

import java.util.List;

//import java.util.Date;

//import javax.imageio.metadata.IIOInvalidTreeException;
//import javax.persistence.*;

//import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

/*
 * Para la entrega 7 se pasa a persistir las búsquedas en Mongo
 * por esta razón se modifican las annotations de Hibernate por
 * annotations de Mongo.
 * Voy a dejar comentadas las annotations de Hibernate por si se
 * precisa volver atrás los cambios. 
 * */

/*
 * Annotations Hibernate
 * @Entity
 * @Table (name = "Busqueda")
*/

@Entity("busqueda")
public class Busqueda {

	/*
	 * 
	 * Annotation Hibernate
	 * @Id
	 * @GeneratedValue
	 * @Column(name = "ID")
	*/
	
	@Id
	private int id;

	public int getId() {
		return id;
	}

	/* 
	 * Annotations Hibernate
	 * 
	 * @Column(name = "FECHABUSQUEDA")
	 * @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	 * */
	
	private long fechaDeBusqueda;

	/*
	 * 
	 * Annotations Hibernate
	 * 
	 * @ElementCollection
	 * @CollectionTable(name = "StringsBuscados", joinColumns = @JoinColumn(name = "BUS_ID"))
	 * @OrderColumn(name = "indice_id")
	 * @Column(name = "STRINGSENCONTRADOS")
	 * */
	@Embedded
	private String[] stringsBuscados;

	/*
	 * 
	 * Annotations Hibernate
	 * @ElementCollection
	 * 	@CollectionTable(name = "PoisEncontrados", joinColumns = @JoinColumn(name = "BUS_ID"))
	 * 	@OrderColumn(name = "indice_id")
	 * 	@Column(name = "IDSENCONTRADOS")
	 * */
	@Embedded
	private int[] idsEncontrados;

	/*
	 * 
	 * Annotations Hibernate
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * @JoinColumn(name = "USUARIO_ID")*/
	private Usuario usuario;

	/*
	 * Annotations Hibernate
	 * 
	 * @Transient
	 * @Column(name = "DURACIONBUSQUEDA")*/
	
	private long duracion;
	
	//@Embedded
    //private List<PuntoDeInteres> pois;

	/*
	 * Se modifica el constructor porque se desnormaliza la búsqueda
	 * en vez de utilizar una lista de ids encontrados se pasa la lista
	 * de pois encontrados directamente
	 */
	
	public Busqueda(String[] stringsBuscados, int[] idsEncontrados, Usuario usuario, Duration duracion,
			DateTime dateTime) {
		this.stringsBuscados = stringsBuscados;
		this.idsEncontrados = idsEncontrados;
		this.fechaDeBusqueda = dateTime.getMillis();
		this.usuario = usuario;
		this.duracion = duracion.getMillis();
	}

	
/*	public Busqueda(String[] stringsBuscados, List<PuntoDeInteres> poisEncontrados, Usuario usuario, Duration duracion,
			DateTime dateTime) {
		this.stringsBuscados = stringsBuscados;
		this.pois = poisEncontrados;
		this.fechaDeBusqueda = dateTime;
		this.usuario = usuario;
		this.duracion = duracion;
	}*/
	
	public DateTime getFechaDeBusquedaJoda() {
		return new DateTime(Long.valueOf(this.fechaDeBusqueda), DateTimeZone.UTC);
	}

	public String[] getStringsBuscados() {
		return stringsBuscados;
	}

	public int[] getIdsEncontrados() {
		return idsEncontrados;
	}

	public boolean coincideBusqueda(String[] x) {
		if (x.length == 0 || x.length != stringsBuscados.length)
			return false;
		boolean coincidencia = true;
		for (int i = 0; i < x.length; i++) {
			coincidencia = coincidencia && (x[i] == null && stringsBuscados[i] == null
					|| (x[i] != null && (x[i].indexOf(stringsBuscados[i])) != 1));
		}
		return coincidencia && Busqueda.fechaValida(new DateTime(Long.valueOf(this.fechaDeBusqueda), DateTimeZone.UTC));
	}

	public static boolean fechaValida(DateTime fecha) {
		DateTime horaActual = new DateTime();
		Duration duracion = new Duration(fecha, horaActual);
		return duracion.getStandardHours() < Constantes.INTERVALO_DEHORAS_CONSIDERA_BUSQUEDA_RECIENTE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Duration getDuracionJoda() {
		return new Duration(duracion);
	}

	public String toString() {
		if (idsEncontrados.length == 0 || stringsBuscados.length == 0)
			return "[" + fechaDeBusqueda + "] No hubo ninguna coincidencia con la busqueda: " + stringsBuscados;

		String s = "[" + fechaDeBusqueda + "] Se buscaron los strings: " + stringsBuscados[0];
		for (int y = 0; y < stringsBuscados.length; y++) {
			s += ", " + stringsBuscados[y];
		}

		s += " - Ids Encontrados: " + idsEncontrados[0];
		for (int i = 1; i < idsEncontrados.length; i++) {
			s += ", " + Integer.valueOf(idsEncontrados[i]);
		}
		return s;
	}

	public BusquedaPojo getPojo() {
		BusquedaPojo bp = new BusquedaPojo();
		bp.setFecha(new DateTime(Long.valueOf(this.fechaDeBusqueda), DateTimeZone.UTC));
		bp.setIds(idsEncontrados);
		bp.setParametros(stringsBuscados);
		bp.setTotal(idsEncontrados.length);
		bp.setUsuario(usuario);
		return bp;
	}

	// Se agrega constructor vacio, no usar, solo para hibernate

	public Busqueda() {

	}

}
