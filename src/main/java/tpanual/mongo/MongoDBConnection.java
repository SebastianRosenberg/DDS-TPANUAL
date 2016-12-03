package tpanual.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import administrador.Busqueda;
import tpanual.main.poi.PuntoDeInteres;
import tpanual.main.poi.PuntoDeInteresWrapper;

public class MongoDBConnection {
	private static MongoDBConnection instance = null;
    private final Datastore datastoreBusquedas;
    private final Datastore datastorePoi;
    
    public static MongoDBConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBConnection();
        }
        return instance;
    }

    private MongoDBConnection(){
    	String dbName = new String("tpanual_bd");
        MongoClient mongo = new MongoClient();
        final Morphia morphia = new Morphia();
        morphia.map(Busqueda.class);
        this.datastoreBusquedas = morphia.createDatastore(mongo, dbName);
        
        final Morphia morphia2 = new Morphia();
        morphia.map(PuntoDeInteresWrapper.class);
        this.datastorePoi = morphia2.createDatastore(mongo, dbName);
        
        datastoreBusquedas.ensureIndexes();
        datastorePoi.ensureIndexes();
        System.out.println("new mongo");
    }

    
    public void agregarBusqueda(Busqueda b){
    	try{
    		datastoreBusquedas.save(b);
    	}catch(Throwable y){
    		y.printStackTrace();
    	}
    }
    
    public List<Busqueda> obtenerBusquedas(String[] strings){
    	    	
    	Query<Busqueda> q = datastoreBusquedas.createQuery(Busqueda.class);
    	q.field("stringsBuscados").equal(strings);
    	List<Busqueda> busquedas = q.asList();
    	return busquedas;
    }
    
    public List<Busqueda> obtenerTodosLasBusquedas(){
    	return datastoreBusquedas.createQuery(Busqueda.class).asList();
    }
    
    public List<PuntoDeInteresWrapper> obtenerPoisDeExternos(String string){
    	
    	Query<PuntoDeInteresWrapper> q = datastorePoi.createQuery(PuntoDeInteresWrapper.class);
    	q.field("stringBuscado").equal(string);
    	List<PuntoDeInteresWrapper> pois = q.asList();
    	return pois;
    }
    
    public void agregarWrapper(PuntoDeInteresWrapper w){
    	try{
    		datastorePoi.save(w);
    	}catch(Throwable y){
    		y.printStackTrace();
    	}
    }
}
