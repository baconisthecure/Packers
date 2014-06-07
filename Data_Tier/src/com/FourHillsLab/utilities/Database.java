package com.FourHillsLab.utilities;

import java.util.ArrayList;
import java.util.List;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Database 
{
	
	
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	
    MongoClient mongodb;
    DB 			database;
    Morphia     morphia;
    Datastore 	dataStore;
    String dbName;
    
    
    /*This singleton implimentation of Database was created by using the Bill Pugh solution as identified in page below 
     * http://howtodoinjava.com/2012/10/22/singleton-design-pattern-in-java/
     * 
     */
    private static class InnerDatabase{
    	 private static final Database INSTANCE = new Database();
    }
    
    public static Database getDatabase()
    {
    	return InnerDatabase.INSTANCE;
    
    }
    
    
    /////////////////////////////////////////////////////Constructors//////////////////////////////////////////////////////////////// 
	protected  Database()  
	{		
		//throw new Exception("Must specify database details");
	    
	}
	
/*	public getDatabase(String server, int port,Class aClass, String store) throws Exception 
	{
		conect( server,  port, aClass,  store,dbName);
	}
	
	public getDatabase(String server, int port,List<Class> aClassList, String store) throws Exception 
	{
		connect(server, port,aClassList,store,dbName);
	}
	
	public getDatabase(String server, int port,String packageName, String store) throws Exception 
	{
		connect(server, port,packageName, store,dbName);
	}*/
	



	/////////////////////////////////////////////////Getters and Setters////////////////////////////////////////////////////////////////
	public MongoClient getmongodb() {
		return mongodb;
	}

	public void setmongodb(MongoClient mongodb) {
		this.mongodb = mongodb;
	}

	public DB getdatabase() {
		return database;
	}

	public void setdatabase(DB database) {
		this.database = database;
	}

	public Morphia getmorphia() {
		return morphia;
	}

	public void setmorphia(Morphia morphia) {
		this.morphia = morphia;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/////////////////////////////////////////////////Methods////////////////////////////////////////////////////////////////
	//Connect to mongoDB with a single class type
	public void conect(String server, int port, Class aClass, String store,String dbName) throws Exception 
	{
	
		logger.debug("Creating Database server = {} port = {}",server,port);
		logger.debug("Database Class = {}", aClass.getCanonicalName());
		logger.debug("Database Datastore Collection = {}", store);
		
		try
		{
			mongodb    = new MongoClient( server, port );
			database   = mongodb.getDB( "morphiaexample" );
			morphia  = new Morphia();		
			morphia.map(aClass);
			dataStore = morphia.createDatastore( mongodb, store );
			this.dbName = dbName; 
		
		}
		catch(Exception e)
		{
			throw e;
		}
		
		logger.debug("Database created succesfully");
		
	}
	
	
	
	public void connect(String server, int port,String packageName, String store,String dbName) throws Exception 
	{
	
		logger.debug("Creating Database server = {} port = {}",server,port);
		logger.debug("Database Package = {}", packageName);
		logger.debug("Database Datastore Collection = {}", store);
		
		try
		{
			mongodb    = new MongoClient( server, port );
			database   = mongodb.getDB( store );
			morphia  = new Morphia();		
		 	morphia.mapPackage(packageName);			 
			
			dataStore = morphia.createDatastore( mongodb, store );
			this.dbName = dbName; 
		
		}
		catch(Exception e)
		{
			throw e;
		}

		logger.debug("Database created succesfully");
		
	}	

	public void map(Class aClass)
	{ 
		logger.info("Mapping {}", aClass.toString());
		morphia.map(aClass);
		
	}

	
	
	public Datastore getDatastore()
	{	
		
		return dataStore;
		
	}
	
	public void close()
	{
		mongodb.close();	
		
	}

}
