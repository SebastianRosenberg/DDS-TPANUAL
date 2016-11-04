package administrador;

import java.util.Date;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import tpanual.jsfcontrollers.pojos.busqueda.BusquedaPojo;
import tpanual.usuario.Usuario;
import tpanual.utilitarios.Constantes;

@Entity
@Table (name = "Busqueda")
public class Busqueda {
	
	@Id @GeneratedValue
 	@Column (name = "ID")
 	private int id;
	
	public int getId() {
		return id;
	}

	@Transient
	@Column (name = "FECHABUSQUEDA")
	@Type (type = "joda.time.Date")
	private DateTime fechaDeBusqueda;
	
	@Transient
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "PALABRAS_BUS", joinColumns = { @JoinColumn(name = "PAL_ID") })
	
	@ElementCollection
	//@OneToMany(cascade = CascadeType.ALL)
	@CollectionTable (
			name="StringsBuscados",
					joinColumns = @JoinColumn(name = "BUS_ID") 
			)
	@OrderColumn (name = "indice_id")
	@Column (name = "STRINGSENCONTRADOS")
	private String[] stringsBuscados;
	
	@ElementCollection
	//@OneToMany(cascade = CascadeType.ALL)
	@CollectionTable (
			name="PoisEncontrados",
					joinColumns = @JoinColumn(name = "BUS_ID") 
			)
	@OrderColumn (name = "indice_id")
	@Column (name = "IDSENCONTRADOS")
	private int[] idsEncontrados;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "USUARIO_ID")
	private Usuario usuario;
	
	@Transient
	@Column (name = "DURACIONBUSQUEDA")
	private Duration duracion;
	
	public Busqueda(String[] stringsBuscados, int[] idsEncontrados, Usuario usuario, Duration duracion, DateTime dateTime){
		this.stringsBuscados=stringsBuscados;
		this.idsEncontrados=idsEncontrados;
		this.fechaDeBusqueda=dateTime;
		this.usuario=usuario;
		this.duracion=duracion;
	}
	
	public DateTime getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}
	public String[] getStringsBuscados() {
		return stringsBuscados;
	}
	public int[] getIdsEncontrados() {
		return idsEncontrados;
	}
	
	public boolean coincideBusqueda(String[] x){
		if (x.length==0 || x.length!=stringsBuscados.length) return false;
		boolean coincidencia=true;
		for (int i=0;i<x.length;i++){
			coincidencia=coincidencia && (x[i]==null && stringsBuscados[i]==null || (x[i]!=null && (x[i].indexOf(stringsBuscados[i]))!=1));
		}
		return coincidencia && Busqueda.fechaValida(fechaDeBusqueda);
	}

	public static boolean fechaValida(DateTime fecha){
		DateTime horaActual=new DateTime();
		Duration duracion=new Duration(fecha, horaActual);
		return duracion.getStandardHours()<Constantes.INTERVALO_DEHORAS_CONSIDERA_BUSQUEDA_RECIENTE;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Duration getDuracion() {
		return duracion;
	}	
	
	public String toString(){
		if (idsEncontrados.length==0 || stringsBuscados.length==0) return "["+fechaDeBusqueda + "] No hubo ninguna coincidencia con la busqueda: " + stringsBuscados;
		
		String s="[" + fechaDeBusqueda + "] Se buscaron los strings: " + stringsBuscados[0] ;
		for (int y=0;y<stringsBuscados.length;y++){
			s+=", " + stringsBuscados[y];
		}
		
		s+=" - Ids Encontrados: " + idsEncontrados[0];
		for (int i=1;i<idsEncontrados.length;i++){
			s+=", " + Integer.valueOf(idsEncontrados[i]);
		}
		return s;
	}
	
	public BusquedaPojo getPojo(){
		BusquedaPojo bp = new BusquedaPojo();
		bp.setFecha(fechaDeBusqueda);
		bp.setIds(idsEncontrados);
		bp.setParametros(stringsBuscados);
		bp.setTotal(idsEncontrados.length);
		bp.setUsuario(usuario);
		return bp;
	}
	
	//Se agrega constructor vacio, no usar, solo para hibernate
	
	public Busqueda(){
		
	}
	
}
