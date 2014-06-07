package com.FourHillsLab.userManagement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.bson.BSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.FourHillsLab.travel.Location;
import com.FourHillsLab.travel.Trip;
import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.userManagement.UserDAO;
import com.FourHillsLab.utilities.Database;
import com.FourHillsLab.utilities.FormalityType;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.FourHillsLab.wardrobe.ClothingType;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.query.Query;

import org.bson.types.ObjectId;

public class UserTest extends User {

	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	static String validDatabaseServer = "localhost";
	static int validPort = 27017;
	static String validStore = "TestStore";
	static String validPackage = "UserManagement";
	static String validDatabase = "TestDatabase";
	static String packageName = "com.FourHillsLab";
	
	static Database db = null;
	static Datastore ds = null;
			
    @Before
    public  void setUp() {
        // one-time initialization code   

    	logger.info("One time set up for UserTest");
    	try
    	{	
    		logger.debug(packageName);			
			
    		db = Database.getDatabase();
    		db.connect(validDatabaseServer, validPort, packageName, validStore, "UserManagementTest");
    		ds = db.getDatastore();
    	}
    	catch(Exception e)
    	{
    		logger.debug("Test setup failed on connection");
    		logger.debug(e.toString());
    		assert(true);
    	}
    }
 
    @After
    public  void tearDown() {
    	logger.info("One time cleanup code for UserTest");
    	//db.getDatastore().getMongo().dropDatabase(validDatabase);    	
    	db.close();
    }

    
    ///////////////////////////////////////////Helper Methods////////////////////////////////////////////////
    

    
    
    ///////////////////////////////////////////Test Methods////////////////////////////////////////////////

	@Test
	public void testToString() 
	{
		String userName = "TestyTester";		
		String firstName = "Tim";
		String middleName = "T";
		String lastName = "Tester";
		String fullName = firstName + " " + middleName + " " + lastName;  
		String sex = "M";
		String email = "TestyTester@Test.com";
		String twitter = "@TestyTester";
		Locale homeLocale = Locale.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(homeLocale);
		calendar.set(2014-1970, 8, 10);
		Date birthday = calendar.getTime();
		
		
		String passportNumber = "LL111AAA222";
		calendar.set(2014-1970+5, 8, 10);
		Date passportExpiration = calendar.getTime();	
		
		Date userCreatedOn = new GregorianCalendar(homeLocale).getInstance().getTime();
		
		
		User userWithUserNameAddressFnMnLn = new User(userName,email,firstName, middleName,lastName);
		assertEquals(String.format("Inactive user with full name %s created on %s",  fullName, userCreatedOn.toString()), userWithUserNameAddressFnMnLn.toString());
	}
	
	@Test
	public void testToJSON() 
	{
		String userName = "TestyTester";		
		String firstName = "Tim";
		String middleName = "T";
		String lastName = "Tester";
		String fullName = firstName + " " + middleName + " " + lastName;  
		String sex = "M";
		String email = "TestyTester@Test.com";
		String twitter = "@TestyTester";
		Locale homeLocale = Locale.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(homeLocale);
		calendar.set(2014-1970, 8, 10);
		Date birthday = calendar.getTime();
		
		
		String passportNumber = "LL111AAA222";
		calendar.set(2014-1970+5, 8, 10);
		Date passportExpiration = calendar.getTime();	
		
		Date userCreatedOn = new GregorianCalendar(homeLocale).getInstance().getTime();
		
		
		User userWithUserNameAddressFnMnLn = new User(userName,email,firstName, middleName,lastName);
		
		StringBuilder buf = new StringBuilder(); 
		
		
		// map the blog entry to a Mongo DBObject
		DBObject blogEntryDbObj = db.getmorphia().toDBObject(userWithUserNameAddressFnMnLn);
		
		
		JSON.serialize(blogEntryDbObj, buf);
		String s = buf.toString();
		
		logger.debug("JSON for {} is: {}",userWithUserNameAddressFnMnLn.toString(), s);
		
		
		assertEquals(String.format("Inactive user with full name %s created on %s",  fullName, userCreatedOn.toString()), userWithUserNameAddressFnMnLn.toString());
	}
	
	
	@Test
	public void testConstructors() 
	{
		
		
		String userName = "TestyTester";
		String fullName = "Tim Tester";
		String firstName = "Tim";
		String middleName = "T";
		String lastName = "Tester";
		String sex = "M";
		String email = "TestyTester@Test.com";
		String twitter = "@TestyTester";
		Locale homeLocale = Locale.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(homeLocale);
		calendar.set(2014-1970, 8, 10);
		Date birthday = calendar.getTime();
		
		
		String passportNumber = "LL111AAA222";
		calendar.set(2014-1970+5, 8, 10);
		Date passportExpiration = calendar.getTime();	
		
		Date userCreatedOn = new GregorianCalendar(homeLocale).getInstance().getTime();
		Boolean activeUser = false;	
		//private TimeZone  currentTimeZone = null;
		//private Currency localCurrency;
		
		
		try
		{				
		    
			User userWithUserName = new User(userName);
			
			
			assertEquals("User name is set properly",userName, userWithUserName.getUserName());
			assertFalse("Default active status false", userWithUserName.getActiveUser());
			
			
			
			User userWithUserNameAddress = new User(userName,email);
			assertEquals("User name is set properly",userName, userWithUserNameAddress.getUserName());
			assertFalse("Default active status false", userWithUserNameAddress.getActiveUser());
			assertEquals("eMail is set properly", email,userWithUserNameAddress.getEmail());
			
			
			
			
			User userWithUserNameAddressFnLn = new User(userName,email,firstName, lastName);
			assertEquals("User name is set properly",userName, userWithUserNameAddressFnLn.getUserName());
			assertFalse("Default active status false", userWithUserNameAddressFnLn.getActiveUser());
			assertEquals("eMail is set properly", email,userWithUserNameAddressFnLn.getEmail());
			assertEquals("First name is set properly", firstName,userWithUserNameAddressFnLn.getFirstName());			
			assertEquals("Last name is set properly", lastName,userWithUserNameAddressFnLn.getLastName());
			assertEquals("Full name is set properly", String.format("%s %s", firstName, lastName),userWithUserNameAddressFnLn.getFullName());

			
			
			
			User userWithUserNameAddressFnMnLn = new User(userName,email,firstName, middleName,lastName);			
			assertEquals("User name is set properly",userName, userWithUserNameAddressFnMnLn.getUserName());
			assertFalse("Default active status false", userWithUserNameAddressFnMnLn.getActiveUser());
			assertEquals("eMail is set properly", email,userWithUserNameAddressFnMnLn.getEmail());
			assertEquals("First name is set properly", firstName,userWithUserNameAddressFnMnLn.getFirstName());
			assertEquals("Middle name is set properly", middleName,userWithUserNameAddressFnMnLn.getMiddleName());
			assertEquals("Last name is set properly", lastName,userWithUserNameAddressFnMnLn.getLastName());
			assertEquals("Full name is set properly", String.format("%s %s %s", firstName,middleName,lastName),userWithUserNameAddressFnMnLn.getFullName());
			
			
			
	    
		}
		catch(Exception e)
		{
			fail(e.toString());
			//fail("Not yet implemented");
		}
		
	
		
	}
	
	@Test
	public void testAddTrip()
	{
		
		
		//Test User Name is minimum value required for a user
		String userName = "TestyTester";
		
		
		//String data for locations. 
		String street = "Main";
		String city = "New City";
		String state = "Grand State";
		String zipcode = "K343l2";
		String country = "Canada";
		
		
		String street2 = "Lower";
		String city2 = "Old City";
		String state2 = "Little Prov";
		String zipcode2 = "90213";
		String country2 = "USA";
		
		String street3 = "Small road";
		String city3 = "Middle of Nowhere";
		String state3 = "State of Vice";
		String zipcode3 = "111113";
		String country3 = "Latvia";
		
		//Create some dates to track departures and arrivals 
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(2014-1970, 8, 10);
		Date depDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH,7);
		Date retDate = calendar.getTime();
		
		
		//Create the user we'll be testing
		User user = new User(userName);
		
		
		//Create the location and trip objects which will be added and then tested
		
		Location location1 = new Location(street, city, state, zipcode, country);
		Location location2 = new Location(street2, city2, state2, zipcode2, country2);
		Location location3 = new Location(street3, city3, state3, zipcode3, country3);
		
		
		Trip trip1 = new Trip(location1,depDate,location2, retDate);	
		Trip trip2 = new Trip(location2,depDate,location1, retDate);
		Trip trip3 = new Trip(location1,depDate,location3, retDate);
		
		//Add the trips to the user and assert that the size of the number of trips is increasing.
		user.addTrip(trip1);
		assertEquals(1,user.getTrips().size());
		user.addTrip(trip2);
		assertEquals(2,user.getTrips().size());
		user.addTrip(trip3);
		assertEquals(3,user.getTrips().size());
		
		
		//Check that the origin (first location in the trip) is stored and retrieved properly.		
		assertEquals(location1, user.getTrips().get(2).getOrigin());
		assertEquals(depDate, user.getTrips().get(2).getDepartureDate());		
		
		//Check that the destination(last location in the trip) is stored and retrieved properly.
		assertEquals(location3, user.getTrips().get(2).getDestination());
		assertEquals(retDate, user.getTrips().get(2).getReturnDate());
		
	}
	
	@Test
	public void testSavingWithDAO() 
	{
		
		
		//Test User Name is minimum value required for a user
		String userName = "TestyTester";
		
		
		//String data for locations. 
		String street = "Main";
		String city = "New City";
		String state = "Grand State";
		String zipcode = "K343l2";
		String country = "Canada";
		
		
		String street2 = "Lower";
		String city2 = "Old City";
		String state2 = "Little Prov";
		String zipcode2 = "90213";
		String country2 = "USA";
		
		String street3 = "Small road";
		String city3 = "Middle of Nowhere";
		String state3 = "State of Vice";
		String zipcode3 = "111113";
		String country3 = "Latvia";
		
		
		//Create the user we'll be testing
		User basicUser = new User(userName);
				
				
		//Create User DAO object for saving, updating and finding users 		
		UserDAO userManager = new UserDAO(db.getmongodb(), db.getmorphia(), validDatabase);		
		userManager.save(basicUser);
		
		long userCount = userManager.count();
		
		assertEquals(1, userCount);
		

		
	}
	
	
	@Test
	public void testFind() 
	{
		
		    

		String userName = "TestyTester";
		String fullName = "Tim Tester";
		String firstName = "Tim";
		String middleName = "T";
		String lastName = "Tester";
		String sex = "M";
		String email = "TestyTester@Test.com";
		String twitter = "@TestyTester";
		
		String userName2 = "TestyTester2";
		String fullName2 = "Tim Tester2";
		String firstName2 = "Tim2";
		String middleName2 = "T2";
		String lastName2 = "Tester2";
		String sex2 = "M";
		String email2 = "TestyTester2@Test.com";
		String twitter2 = "@TestyTester2";
		
		String userName3 = "TestyTester3";
		String fullName3 = "Tim Tester3";
		String firstName3 = "Tim3";
		String middleName3 = "T3";
		String lastName3 = "Tester3";
		String sex3 = "M";
		String email3 = "TestyTester3@Test.com";
		String twitter3 = "@TestyTester3";
		
		
		Locale homeLocale = Locale.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(homeLocale);
		calendar.set(2014-1970, 8, 10);
		Date birthday = calendar.getTime();
		
		
		String passportNumber = "LL111AAA222";
		calendar.set(2014-1970+5, 8, 10);
		Date passportExpiration = calendar.getTime();	
		
		Date userCreatedOn = new GregorianCalendar(homeLocale).getInstance().getTime();
		Boolean activeUser = false;	
		//private TimeZone  currentTimeZone = null;
	
			
			//String data for locations. 
			String street = "Main";
			String city = "New City";
			String state = "Grand State";
			String zipcode = "K343l2";
			String country = "Canada";
			
			
			String street2 = "Lower";
			String city2 = "Old City";
			String state2 = "Little Prov";
			String zipcode2 = "90213";
			String country2 = "USA";
			
			String street3 = "Small road";
			String city3 = "Middle of Nowhere";
			String state3 = "State of Vice";
			String zipcode3 = "111113";
			String country3 = "Latvia";
			
			//Create some dates to track departures and arrivals 			
			calendar.set(2014-1970, 8, 10);
			Date depDate = calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH,7);
			Date retDate = calendar.getTime();
			
			
			//Create the user we'll be testing
			User user1 = new User(userName,email,firstName, middleName,lastName);
			User user2 = new User(userName2,email2,firstName2, middleName2,lastName2);
			User user3 = new User(userName3,email3,firstName3, middleName3,lastName3);
			
			
			//Create the location and trip objects which will be added and then tested
			
			Location location1 = new Location(street, city, state, zipcode, country);
			Location location2 = new Location(street2, city2, state2, zipcode2, country2);
			Location location3 = new Location(street3, city3, state3, zipcode3, country3);
			
			
			Trip trip1 = new Trip(location1,depDate,location2, retDate);	
			Trip trip2 = new Trip(location2,depDate,location1, retDate);
			Trip trip3 = new Trip(location1,depDate,location3, retDate);
			
			//Add the trips to the user and assert that the size of the number of trips is increasing.
			user1.addTrip(trip1);			
			user2.addTrip(trip2);			
			user3.addTrip(trip3);
			
						
			
			//Create User DAO object for saving, updating and finding users 		
			UserDAO userManager = new UserDAO(db.getmongodb(), db.getmorphia(), validDatabase);
			
			
			//Save the user that we created
			userManager.save(user1);
			userManager.save(user2);
			userManager.save(user3);
			
			//Find the second user we just created
			List<User> foundUsers = userManager.findByUserName(userName2);
			int size = foundUsers.size();
			assertNotEquals(0,foundUsers.size());
			
			User foundUser = foundUsers.get(0);
			
			assertEquals("User name is set properly",userName2, foundUser.getUserName());
			assertFalse("Default active status false", foundUser.getActiveUser());
			assertEquals("eMail is set properly", email2,foundUser.getEmail());
			assertEquals("First name is set properly", firstName2,foundUser.getFirstName());
			assertEquals("Middle name is set properly", middleName2,foundUser.getMiddleName());
			assertEquals("Last name is set properly", lastName2,foundUser.getLastName());
			assertEquals("Full name is set properly", String.format("%s %s %s", firstName2,middleName2,lastName2),foundUser.getFullName());
			
			
			//Find all three users
			foundUsers = userManager.findAllUsers();			
			assertEquals(3,foundUsers.size());
			logger.debug("Result of finding 3 users is {}", foundUsers.size());
			
			
/*			//Check that the origin (first location in the trip) is stored and retrieved properly.		
		//	assertEquals(location1.getCity(), foundUser.getTrips().get(2).getOrigin().getCity());
			assertEquals(depDate, foundUser.getTrips().get(2).getDepartureDate());		
			
			//Check that the destination(last location in the trip) is stored and retrieved properly.
		//	assertEquals(location3.getId(), foundUser.getTrips().get(2).getDestination().getId());
			assertEquals(retDate, foundUser.getTrips().get(2).getReturnDate());*/
		
	}
	
	@Test
	public void testUpdate() 
	{
		
		    

		String userName = "TestyTester";
		String fullName = "Tim Tester";
		String firstName = "Tim";
		String middleName = "T";
		String lastName = "Tester";
		String sex = "M";
		String email = "TestyTester@Test.com";
		String twitter = "@TestyTester";
		Locale homeLocale = Locale.getDefault();
		GregorianCalendar calendar = new GregorianCalendar(homeLocale);
		calendar.set(2014-1970, 8, 10);
		Date birthday = calendar.getTime();
		
		
		String passportNumber = "LL111AAA222";
		calendar.set(2014-1970+5, 8, 10);
		Date passportExpiration = calendar.getTime();	
		
		Date userCreatedOn = new GregorianCalendar(homeLocale).getInstance().getTime();
		Boolean activeUser = false;	
		//private TimeZone  currentTimeZone = null;
	
			
			//String data for locations. 
			String street = "Main";
			String city = "New City";
			String state = "Grand State";
			String zipcode = "K343l2";
			String country = "Canada";
			
			
			String street2 = "Lower";
			String city2 = "Old City";
			String state2 = "Little Prov";
			String zipcode2 = "90213";
			String country2 = "USA";
			
			String street3 = "Small road";
			String city3 = "Middle of Nowhere";
			String state3 = "State of Vice";
			String zipcode3 = "111113";
			String country3 = "Latvia";
			
			//Create some dates to track departures and arrivals 			
			calendar.set(2014-1970, 8, 10);
			Date depDate = calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH,7);
			Date retDate = calendar.getTime();
			
			
			//Create the user we'll be testing
			User userWithUserNameAddressFnMnLn = new User(userName,email,firstName, middleName,lastName);	
			
			
			//Create the location and trip objects which will be added and then tested
			
			Location location1 = new Location(street, city, state, zipcode, country);
			Location location2 = new Location(street2, city2, state2, zipcode2, country2);
			Location location3 = new Location(street3, city3, state3, zipcode3, country3);
			
			
			Trip trip1 = new Trip(location1,depDate,location2, retDate);	
			Trip trip2 = new Trip(location2,depDate,location1, retDate);
			Trip trip3 = new Trip(location1,depDate,location3, retDate);
			
			//Add the trips to the user and assert that the size of the number of trips is increasing.
			userWithUserNameAddressFnMnLn.addTrip(trip1);
			assertEquals(1,userWithUserNameAddressFnMnLn.getTrips().size());
			userWithUserNameAddressFnMnLn.addTrip(trip2);
			assertEquals(2,userWithUserNameAddressFnMnLn.getTrips().size());
			userWithUserNameAddressFnMnLn.addTrip(trip3);
			assertEquals(3,userWithUserNameAddressFnMnLn.getTrips().size());
						
			
			//Create User DAO object for saving, updating and finding users 		
			UserDAO userManager = new UserDAO(db.getmongodb(), db.getmorphia(), validDatabase);
			
			
			//Save the user that we created
			userManager.save(userWithUserNameAddressFnMnLn);
			
			List<User> foundUsers = userManager.find().asList();
			
			
			assertEquals(1, foundUsers.size());
			
			//Find the user we just created
			User foundUser = foundUsers.get(0);
			
			assertEquals("User name is set properly",userName, foundUser.getUserName());
			assertFalse("Default active status false", foundUser.getActiveUser());
			assertEquals("eMail is set properly", email,foundUser.getEmail());
			assertEquals("First name is set properly", firstName,foundUser.getFirstName());
			assertEquals("Middle name is set properly", middleName,foundUser.getMiddleName());
			assertEquals("Last name is set properly", lastName,foundUser.getLastName());
			assertEquals("Full name is set properly", String.format("%s %s %s", firstName,middleName,lastName),foundUser.getFullName());
			
			//Check that the origin (first location in the trip) is stored and retrieved properly.		
			assertEquals(location1.getCity(), foundUser.getTrips().get(2).getOrigin().getCity());
			assertEquals(depDate, foundUser.getTrips().get(2).getDepartureDate());		
			
			//Check that the destination(last location in the trip) is stored and retrieved properly.
			assertEquals(location3.getId(), foundUser.getTrips().get(2).getDestination().getId());
			assertEquals(retDate, foundUser.getTrips().get(2).getReturnDate());
		
	}
	
	@Test
	public void testDeleteWithDAO() 
	{
		
		
		//Test User Name is minimum value required for a user
		String userName = "TestyTester";
		
		
		//String data for locations. 
		String street = "Main";
		String city = "New City";
		String state = "Grand State";
		String zipcode = "K343l2";
		String country = "Canada";
		
		
		String street2 = "Lower";
		String city2 = "Old City";
		String state2 = "Little Prov";
		String zipcode2 = "90213";
		String country2 = "USA";
		
		String street3 = "Small road";
		String city3 = "Middle of Nowhere";
		String state3 = "State of Vice";
		String zipcode3 = "111113";
		String country3 = "Latvia";
		
		
		//Create the user we'll be testing
		User basicUser = new User(userName);
				
				
		//Create User DAO object for saving, updating and finding users 		
		UserDAO userManager = new UserDAO(db.getmongodb(), db.getmorphia(), validDatabase);		
		userManager.save(basicUser);
		
		long userCount = userManager.count();		
		assertEquals(1, userCount);
		
		userManager.deleteById(basicUser.get_Id());
		
		userCount = userManager.count();		
		assertEquals(0, userCount);
		

		
	}

}
