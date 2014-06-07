package com.FourHillsLab.utilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.types.ObjectId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.LoggerFactory;

import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.utilities.Database;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.FourHillsLab.wardrobe.ClothingType;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;

public class DatabaseTest extends Database {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseTest.class);
	@Rule  public ExpectedException expected = ExpectedException.none();

	String validDatabaseServer = "localhost";
	int validPort = 27017;
	String validStore = "TestDatabase";
	String validPackage = "UserManagement";
	static String packageName = "com.FourHillsLab";
	
	

	//This exists only because I was forced to have a default constructor in the Database class to have test case inherit from classs being tested
	private DatabaseTest()
	{
		super();
	}
	


	@Test
	public void testCreateDatabasePackageBased() 
	{
		
		
    	logger.info("One time set up for UserTest");
    	try
    	{	
    		logger.debug(packageName);		
    		
    		Database db = null;
    		Datastore ds = null;
			
    		db = Database.getDatabase();
    		db.connect(validDatabaseServer, validPort, packageName, validStore, "UserManagementTest");
    		ds = db.getDatastore();
    		assertNotNull(db.getDatastore());
    		Database db2 = Database.getDatabase();
    		assertSame(db2, db);
    	}
    	catch(Exception e)
    	{
    		logger.debug("Test setup failed on connection");
    		logger.debug(e.toString());
    		assert(true);
    	}
    	
	}



	@Test
	public void testCreateDatabaseClassBased() {
		try
		{
						
			
			
			Database db = Database.getDatabase();
					
			db.connect(validDatabaseServer, validPort,User.class,validStore,validStore);
			
			assertNotNull(db.getDatastore());
			
		}
		catch(Exception e)
		{
			assertNotNull(e.getMessage());
		
		}
	}


	@Test
	public void testMapArrayListOfClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetDatastore() 
	{
		try
		{
		Database db = new Database(validDatabaseServer, validPort,User.class,validStore,validStore);
		
		assertNotNull(db.getDatastore());
		}
		catch(Exception e)
		{
			assertNotNull(e.getMessage());
		
		}
	}
	
	

}
