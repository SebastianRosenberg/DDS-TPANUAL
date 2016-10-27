package tpanual.main;

import javax.persistence.*;

/**
 * Esta clase tiene implementado el patron builder. Se crea asi:
 * 
 * Direccion d=new Direccion.DireccionBuilder().barrio("barrio").callePrincipal("localidad").departamento("departamento").crearDireccion();
 * 
 * Vas anidando las llamadas para cada uno de los campos, de esta manera podes crear objetos Direccion sin todos los campos cargados.
 * Ademas se hace mas simple la creacion
 * 
 * @author dipatata
 *
 */
@Entity
@Table(name = "DIRECCION")
public class Direccion {
	
	public String toString(){
		return callePrincipal+" " +entreCalle1+" " +entreCalle2+" " +numero+" " +piso+" " +departamento+" " +unidad+" " +codigoPostal+" " 
		+barrio+" " +localidad+" " +provincia+" " +pais;
	}
	@Id @GeneratedValue
	@Column(name = "id")	
	private int id;
	@Column(name = "CALLE_PRINCIPAL")
	private String callePrincipal;
	@Column(name = "ENTRE_CALLE_1")
	private String entreCalle1;
	private String entreCalle2;
	private String numero;
	private String piso;
	private String departamento;
	private String unidad;
	private String codigoPostal;
	private String barrio;
	private String localidad;
	private String provincia;
	private String pais;
	
	public Direccion(String callePrincipal, String entreCalle1, String entreCalle2, String numero, String piso, String departamento, String unidad, 
			String codigoPostal, String barrio, String localidad, String provincia, String pais){	
		
		this.callePrincipal=callePrincipal;
		this.entreCalle1=entreCalle1;
		this.entreCalle2=entreCalle2;
		this.numero=numero;
		this.piso=piso;
		this.departamento=departamento;
		this.unidad=unidad;
		this.codigoPostal=codigoPostal;
		this.barrio=barrio;
		this.localidad=localidad;
		this.provincia=provincia;
		this.pais=pais;		
			
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Direccion)) 
			return false;
		Direccion d=(Direccion) o;
		return d.toString().equals(this.toString());
		
	}
	
	public String getCallePrincipal() {
		return callePrincipal;
	}
	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}
	public String getEntreCalle1() {
		return entreCalle1;
	}
	public void setEntreCalle1(String entreCalle1) {
		this.entreCalle1 = entreCalle1;
	}
	public String getEntreCalle2() {
		return entreCalle2;
	}
	public void setEntreCalle2(String entreCalle2) {
		this.entreCalle2 = entreCalle2;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public static class DireccionBuilder{
		private String callePrincipal;
		private String entreCalle1;
		private String entreCalle2;
		private String numero;
		private String piso;
		private String departamento;
		private String unidad;
		private String codigoPostal;
		private String barrio;
		private String localidad;
		private String provincia;
		private String pais;
		
		public DireccionBuilder callePrincipal(String callePrincipal){
			this.callePrincipal=callePrincipal;
			return this;
		}
		
		public DireccionBuilder entreCalle1(String entreCalle1){
			this.entreCalle1=entreCalle1;
			return this;
		}	
		
		public DireccionBuilder entreCalle2(String entreCalle2){
			this.entreCalle2=entreCalle2;
			return this;
		}			
		
		public DireccionBuilder numero(String numero){
			this.numero=numero;
			return this;
		}			
		public DireccionBuilder piso(String piso){
			this.piso=piso;
			return this;
		}		
		
		public DireccionBuilder departamento(String departamento){
			this.departamento=departamento;
			return this;
		}			
		
		public DireccionBuilder unidad(String unidad){
			this.unidad=unidad;
			return this;
		}				
		
		public DireccionBuilder codigoPostal(String codigoPostal){
			this.codigoPostal=codigoPostal;
			return this;
		}		
		
		public DireccionBuilder barrio(String barrio){
			this.barrio=barrio;
			return this;
		}		
		
		public DireccionBuilder localidad(String localidad){
			this.localidad=localidad;
			return this;
		}		
		
		public DireccionBuilder provincia(String provincia){
			this.provincia=provincia;
			return this;
		}	
		
		public DireccionBuilder pais(String pais){
			this.pais=pais;
			return this;
		}			
		
		public Direccion crearDireccion(){
			return new Direccion(callePrincipal, entreCalle1, entreCalle2, numero, piso, departamento, unidad, codigoPostal, barrio, localidad, provincia, pais);
		}
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
}
