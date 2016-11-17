package tpanual.mongo;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

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

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        //morphia.mapPackage("src.main.java.tpanual.administrador.busqueda");
        morphia.map(Busqueda.class);
        // create the Datastore connecting to the default port on the local host
        this.datastore = morphia.createDatastore(mongo, dbName);
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return this.datastore;
    }
}
