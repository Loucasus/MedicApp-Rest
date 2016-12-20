package medicapp.rest;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBSingleton {

	private static MongoDBSingleton mDbSingleton;
	
	private static MongoClient mongoClient;

	private  static MongoClientURI uri;
    
	private static DB db ;
	
	
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
	
	public DB getTestdb(){
		if(mongoClient == null){
			try {
				MongoClientURI uri = new MongoClientURI("mongodb://userCPE:userCPE2017@ds133378.mlab.com:33378/medicappcpe");
				mongoClient = new MongoClient(uri);
			} catch (UnknownHostException e) {
				return null;
			}
		}
		if(db == null)
			db = mongoClient.getDB(uri.getDatabase());
		/*if(!db.isAuthenticated()){
			boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
		}*/
		return db;
	}
}
