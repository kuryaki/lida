package lida;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DB {
	
	private final Datastore datastore;
	private final MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
	private final Morphia morphia = new Morphia();
	private static DB db = new DB();

	private DB() {
	
		morphia.mapPackage("model");	
		datastore = morphia.createDatastore(mongoClient, "lida");
		datastore.ensureIndexes();
	}
	
	public static DB getDB() {
		return db;
	}
	
	public Datastore getDatastore() {
		return this.datastore;
	};

}
