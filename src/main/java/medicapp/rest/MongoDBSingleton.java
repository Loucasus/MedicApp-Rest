package medicapp.rest;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBSingleton {

	private static MongoDBSingleton mDbSingleton;
	
	private static MongoClient mongoClient;

	private  static MongoClientURI uri;
    
	private static MongoDatabase db ;
	
	
	private static final String dbHost = "ds133378.mlab.com";
	private static final int dbPort = 33378;
	private static final String dbName = "medicappcpe";
	private static final String dbUser = "userCPE";
	private static final String dbPassword = "userCPE2017";
	
	private MongoDBSingleton(){};
	
	public static MongoDBSingleton getInstance(){
		if(mDbSingleton == null){
			mDbSingleton = new MongoDBSingleton();
		}
		return mDbSingleton;
	} 
	
	public MongoDatabase getTestdb(){
		if(mongoClient == null){
			uri = new MongoClientURI("mongodb://PozorVlak:BadGones87@ds133378.mlab.com:33378/medicappcpe");
			mongoClient = new MongoClient(uri);
		}
		if(db == null)
			db = mongoClient.getDatabase(uri.getDatabase());
		/*if(!db.isAuthenticated()){
			boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
		}*/
		return db;
	}
}
