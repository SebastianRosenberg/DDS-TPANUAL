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

public class MongoDBConnection {
	private static MongoDBConnection instance = null;
    private final Datastore datastore;    
    
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
        this.datastore = morphia.createDatastore(mongo, dbName);
        datastore.ensureIndexes();
        System.out.println("new mongo");
    }

    
    public void agregarBusqueda(Busqueda b){
    	datastore.save(b);
    }
    
    public List<Busqueda> obtenerBusquedas(String[] strings){
    	    	
    	Query<Busqueda> q = datastore.createQuery(Busqueda.class);
    	q.field("stringsBuscados").equal(strings);
    	List<Busqueda> busquedas = q.asList();
    	return busquedas;
    }
    
    public List<Busqueda> obtenerTodosLasBusquedas(){
    	return datastore.createQuery(Busqueda.class).asList();
    }
}
