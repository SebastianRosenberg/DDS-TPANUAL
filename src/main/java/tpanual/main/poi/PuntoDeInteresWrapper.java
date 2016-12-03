package tpanual.main.poi;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "ServiciosExternos_Buffer")
public class PuntoDeInteresWrapper {
	private String stringBuscado;
	
	@Embedded
	private List<PuntoDeInteres> pois_embbebed;
	
	@Id
	private ObjectId  id;
	
	public PuntoDeInteresWrapper(List<PuntoDeInteres> lista, String x){
		pois_embbebed=lista;
		stringBuscado=x;
	}
	
	public PuntoDeInteresWrapper(){
		
	}
	
	

	public String getStringBuscado() {
		return stringBuscado;
	}

	public void setStringBuscado(String stringBuscado) {
		this.stringBuscado = stringBuscado;
	}



	public List<PuntoDeInteres> getPois_embbebed() {
		return pois_embbebed;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setPois_embbebed(List<PuntoDeInteres> pois_embbebed) {
		this.pois_embbebed = pois_embbebed;
	}
	
	
}
