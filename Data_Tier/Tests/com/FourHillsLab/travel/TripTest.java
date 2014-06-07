package com.FourHillsLab.travel;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import TestApp.DataApplication;

import com.FourHillsLab.travel.Location;
import com.FourHillsLab.travel.Trip;
import com.FourHillsLab.travel.TripDAO;
import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.userManagement.UserDAO;
import com.FourHillsLab.utilities.Database;
import com.FourHillsLab.utilities.FormalityType;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothesBinDAO;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.FourHillsLab.wardrobe.ClothingType;
import com.github.jmkgreen.morphia.Datastore;
import com.mongodb.MongoException;

public class TripTest extends Trip{

	private static final Logger logger = LoggerFactory.getLogger(TripTest.class);
	
	
	static String validDatabaseServer = "localhost";
	static int validPort = 27017;
	static String validStore = "TestStore";
	static String validPackage = "UserManagement";
	static String validDatabase = "TestDatabase";
	static String packageName = "com.FourHillsLab";
	
	static Database db = null;
	static Datastore ds = null;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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


	@Test
	public void testGetOrigin() 
	{

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
		
		
		Location originTest = new Location(street, city, state, zipcode, country);
		Location destinationTest = new Location(street2, city2, state2, zipcode2, country2);
		
		//Date plus the next day 
		Date departureDate = new GregorianCalendar().getInstance().getTime();
		Date returnDate = new Date (departureDate.getTime() + 1000 *60*60*24);
		
		
		Trip testTrip = new Trip(originTest , departureDate, destinationTest ,  returnDate);  

		logger.debug("origin is {}", originTest.toString());
		assertEquals(originTest.toString(), testTrip.getOrigin().toString());
					
			
			
		
	}

	@Test
	public void testGetDestination() 
	{
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
		
		
		Location originTest = new Location(street, city, state, zipcode, country);
		Location destinationTest = new Location(street2, city2, state2, zipcode2, country2);
		
		//Date plus the next day 
		Date departureDate = new GregorianCalendar().getInstance().getTime();
		Date returnDate = new Date (departureDate.getTime() + 1000 *60*60*24);
		
		
		Trip testTrip = new Trip(originTest , departureDate, destinationTest ,  returnDate);  

		
		logger.debug("destination is {}", destinationTest.toString());
		assertEquals(destinationTest.toString(), testTrip.getDestination().toString());
	}

	@Test
	public void testDAO()
	{
			
		
		
			
		
		User u = new User("Traveler", "a@packer.com", "kelly", "dawn", "Belo");
		ClothesBin bin = new ClothesBin();
		ClothesBin bin2 = new ClothesBin();
		ClothesBinDAO binDao = new ClothesBinDAO(db.getmongodb(), db.getmorphia(), db.getDbName());
		binDao.save(bin);
		u.setBin(bin);
		UserDAO userDao =  new UserDAO(db.getmongodb(), db.getmorphia(), db.getDbName());
		userDao.save(u);
		
		Location aLocation = new Location("72 Jarlan", "Ottawa", "Ontario", "K2L 3K9", "Canada");
		Location aLocation2 = new Location("Town road", "Morristown", "Nova Scotia", "L2K 3V9", "Canada");
		
		Trip aTrip1 = new Trip(u,aLocation,new Date(), aLocation2, new Date());
		//aTrip1.setPackingList(bin2);
		
		u.addTrip(aTrip1);
		
		db.getDatastore().save(u);
	
		try
		{
			TripDAO tripDao = new TripDAO(db.getmongodb(), db.getmorphia(), db.getDbName());
			
			tripDao.save(aTrip1);
			logger.debug("trip count {}", tripDao.find().countAll());
			for(Trip aTrip: tripDao.find().asList())
			{
				//logger.debug("Trip details Origin = {}, Destination = {}", aTrip.getOrigin().toString(),aTrip.getDestination().toString());
				
				
			}
		}
		catch(Exception e)
		{
			logger.debug(e.toString());
			
		}		
		
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
			user2.addTrip(trip3);
			
						
			
			//Create User DAO object for saving, updating and finding users 		
			UserDAO userManager = new UserDAO(db.getmongodb(), db.getmorphia(), validDatabase);
			
			
			//Save the user that we created
			userManager.save(user1);
			userManager.save(user2);
			
			TripDAO tripManager = new TripDAO(db.getmongodb(), db.getmorphia(), validDatabase);
			
			
			//Find the second user we just created
			List<Trip> foundTrips = tripManager.findByUserName(userName2);
			int size = foundTrips.size();
			assertEquals(2,foundTrips.size());

			assertEquals("First trip origin set",location2.toString(), foundTrips.get(0).getOrigin().toString());
			assertEquals("First trip departure set",depDate, foundTrips.get(0).getDepartureDate());
			assertEquals("First trip destination set",location1.toString(), foundTrips.get(0).getDestination().toString());
			assertEquals("First trip departure set",retDate, foundTrips.get(0).getReturnDate());


			assertEquals("First trip origin set",location2.toString(), foundTrips.get(0).getOrigin().toString());
			assertEquals("First trip departure set",depDate, foundTrips.get(0).getDepartureDate());
			assertEquals("First trip destination set",location1.toString(), foundTrips.get(0).getDestination().toString());
			assertEquals("First trip departure set",retDate, foundTrips.get(0).getReturnDate());
			
			
			
			//Find all three users
//			foundUsers = userManager.findAllUsers();			
//			assertEquals(3,foundUsers.size());
//			logger.debug("Result of finding 3 users is {}", foundUsers.size());
			
			
/*			//Check that the origin (first location in the trip) is stored and retrieved properly.		
		//	assertEquals(location1.getCity(), foundUser.getTrips().get(2).getOrigin().getCity());
			assertEquals(depDate, foundUser.getTrips().get(2).getDepartureDate());		
			
			//Check that the destination(last location in the trip) is stored and retrieved properly.
		//	assertEquals(location3.getId(), foundUser.getTrips().get(2).getDestination().getId());
			assertEquals(retDate, foundUser.getTrips().get(2).getReturnDate());*/
		
	}
}
