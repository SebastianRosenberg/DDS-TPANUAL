package tpanual.jsfBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AccionesBean {
private List<String> acciones = new ArrayList<String>();
private String accion;

@PostConstruct
public void init(){
	
	//acciones
	
	acciones.add("unaAccion");
	acciones.add("otraAccion");
}

public List<String> getAcciones() {
	return acciones;
}

public void setAcciones(List<String> acciones) {
	this.acciones = acciones;
}

public String getAccion() {
	return accion;
}

public void setAccion(String accion) {
	this.accion = accion;
}

}
