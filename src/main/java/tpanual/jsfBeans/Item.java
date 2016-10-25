package tpanual.jsfBeans;

public class Item {
	private String value;
	private int id;
	
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
    	System.out.println("Protto: " + value);
        this.value = value;
    }
    
    public void setId (int nuevoId){
    	this.id = nuevoId;
    }
    
    public void updateValue(String value) {
    	
    }

    public String toString() {
        return String.format("Item[value=%s]", value);
    }
}

