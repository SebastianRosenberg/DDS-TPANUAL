package tpanual.webservices.recursos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PoiPojo")
public class PoiPojo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	

	public PoiPojo(){}
	
	public PoiPojo(int id){
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

}
