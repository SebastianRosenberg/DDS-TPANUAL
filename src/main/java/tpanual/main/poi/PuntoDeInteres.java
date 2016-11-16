package tpanual.main.poi;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

import administrador.Mapa;
import tpanual.jsfcontrollers.pojos.poi.PoiPojo;
import tpanual.main.Dias;
import tpanual.main.direccion.Direccion;
import tpanual.utilitarios.Utilitarios;

@Entity
@Table (name = "PUNTO_DE_INTERES")
public class PuntoDeInteres {
	@Id @Column (name = "ID")
	private int id;
	@Column (name = "LATITUD")
	private double latitud;
	@Column (name = "LONGITUD")
	private double longitud;
	@Column (name = "NOMBRE")
	private String nombre;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "DIRECCION_ID" )
	private Direccion direccion;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn ( name = "TIPO_PUNTO_INTERES_ID")
	private TipoPuntoInteres tipo;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "POI_PALABRAS", joinColumns = { @JoinColumn(name = "POI_ID") }, inverseJoinColumns = { @JoinColumn(name = "PALABRA_CLAVE_ID") })
	private List<PalabraClave> palabrasClaves;
	@Column (name = "DADO_DE_BAJA")
	private boolean dadoDeBaja;
	@Column (name = "FECHA_DE_BAJA")
	private DateTime fechaBaja;

	
	//Para modificar POI
	@Transient
	private static String[] fieldsModificables={"latitud", "longitud", "nombre", "direccion", "tipo", "palabrasClaves"};
	@Transient
	private static int maxId=0;
	
	public PuntoDeInteres(double latitud, double longitud, String nombre, Direccion direccion, List<PalabraClave> palabrasClaves, TipoPuntoInteres tipo) {
		this.id=++maxId;
		this.latitud=latitud;
		this.longitud=longitud;
		this.nombre=nombre;
		this.direccion=direccion;
		this.tipo=tipo;
		this.palabrasClaves=palabrasClaves;
		this.dadoDeBaja=false;
	}
	
	//DEFAULT CONSTRUCTOR PARA HIBERNATE, NO USAR
	public PuntoDeInteres(){
		
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public String getNombre() {
		return nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public TipoPuntoInteres getTipo() {
		return tipo;
	}

	public String coordenadas(){
		return this.latitud + "," + this.longitud;
	}
	

	
	public boolean cercanoA(double unaLatitud, double unaLongitud, int comunaId){
		
		return this.tipo.cercanoEntre(this.latitud, this.longitud, unaLatitud, unaLongitud, comunaId);
}
	

	public boolean buscarCoincidencia(String x){
		
		return Utilitarios.buscarPalabraEnPalabrasClave(x, palabrasClaves) || tipo.coincidencia(x)|| (nombre.indexOf(x) != -1);

	}

	public boolean estaDisponible(int dia, int hora, String x) {
		return tipo.estaDisponible(dia, hora, x);
	}

	public Integer getId() {
		return id;
	}
	

	/*
	 * Seba: Agrego los m�todos de c�lculo de distancia en la clase de punto de inter�s
	 * para que se use todo desde el administradr sin acceder directamente a mapa
	 * 
	 * 
	 */
	//public static void main (String[] args) throws java.lang.Exception
		//{
			//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
			//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
			//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
		//}

		public void setId(int id) {
		this.id = id;
	}

		public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
			double theta = lon1 - lon2;
			double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
			dist = Math.acos(dist);
			dist = rad2deg(dist);
			dist = dist * 60 * 1.1515;
			if (unit == "K") {
				dist = dist * 1.609344;
			} else if (unit == "N") {
				dist = dist * 0.8684;
			}

			return (dist);	
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::	This function converts decimal degrees to radians						 :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double deg2rad(double deg) {
			return (deg * Math.PI / 180.0);
		}

		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::	This function converts radians to decimal degrees						 :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double rad2deg(double rad) {
			return (rad * 180 / Math.PI);
		}
		

	public boolean afectarCambios(PuntoDeInteres poi) {
		Class<?> clase=this.getClass();
		for (int a=0;a<fieldsModificables.length;a++){
			try{
				Field f=clase.getDeclaredField(fieldsModificables[a]);
				Object s1=f.get(poi);
				Object s2=f.get(this);
				if (!s1.equals(s2))
					f.set(this, s1);
				
			}catch(NoSuchFieldException nsfe){
				nsfe.printStackTrace();
				System.out.println("Error, no existe el field " + fieldsModificables[a] + " en la clase " + clase.getCanonicalName());
				return false;
			}catch(IllegalAccessException iae){
				iae.printStackTrace();
				System.out.println("Error, no se puede acceder a " + fieldsModificables[a] + " en la clase " + clase.getCanonicalName());
				return false;
			}
			
		}
		return true;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof PuntoDeInteres))
			return false;
		else{
			PuntoDeInteres pdi=(PuntoDeInteres) o;
			if (pdi.getId().equals(this.id))
				return true;
			else
				return false;
		}

	}

	public boolean isDadoDeBaja() {
		return dadoDeBaja;
	}

	public void setDadoDeBaja(boolean dadoDeBaja) {
		this.dadoDeBaja = dadoDeBaja;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setTipo(TipoPuntoInteres tipo) {
		this.tipo = tipo;
	}

	public void setPalabrasClaves(List<PalabraClave> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

	public static void setMaxId(int maxId) {
		PuntoDeInteres.maxId = maxId;
	}

	public DateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(DateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	


	public PoiPojo getPojo(){
		return tipo.convertir(this);
	}
	
}

