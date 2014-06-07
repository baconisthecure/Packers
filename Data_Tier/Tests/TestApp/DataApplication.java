package TestApp;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.FourHillsLab.travel.Location;
import com.FourHillsLab.travel.Trip;
import com.FourHillsLab.travel.TripDAO;
import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.utilities.Database;
import com.FourHillsLab.utilities.FormalityType;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothesBinDAO;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.FourHillsLab.wardrobe.ClothingItemDAO;
import com.FourHillsLab.wardrobe.ClothingType;
import com.github.jmkgreen.morphia.Datastore;



import com.mongodb.MongoException;





public class DataApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DataApplication.class);
	static String validDatabaseServer = "localhost";
	static int validPort = 27017;
	static String validStore = "TestDatabase";
	static String validPackage = "UserManagement";

	static Database db = null;
	static Datastore ds = null;

	public static void main(String[] args) 
	{
		List<Class> mappings = new ArrayList<Class>();
		mappings.add(User.class);			
		mappings.add(ClothesBin.class);
		mappings.add(ClothingItem.class);
		mappings.add(ClothingType.class);
		mappings.add(FormalityType.class);
		
		
		try
		{
		
		
		db = new Database(validDatabaseServer, validPort, mappings, validStore, validStore);
		
			
		}
		catch( UnknownHostException e )
		{
			logger.error( "MongoDB host not found" );
		}
		catch( MongoException e )
		{
			logger.error( "Error attempting MongoDB connection for {} with message {}", mappings, e );
		}
		catch(Exception e)
		{
			
			logger.error( "General Error attempting MongoDB connection for {} with message {}", mappings, e );
			
		}
		
		char x = '@';
		String s = new String().replace(x, x);
		
		User u = new User("bacon", "a@a.com", "Andrew", "Olatunde", "Belo");
		FormalityType fType = new FormalityType();
		fType.setIsCasuaEvent(true);
		fType.setIsFancyEvent(true);
		
		FormalityType fType2 = new FormalityType();
		fType2.setIsBusinessEvent(true);
		fType2.setIsFancyEvent(true);
		
		ClothingType cType = new ClothingType();
		cType.setIsActive(true);
		cType.setItemType("Pants");
		
		
		ClothingType cType2 = new ClothingType();
		cType2.setIsActive(true);
		cType2.setItemType("Socks");
		
		
		ClothingItemDAO clothingDAO = new ClothingItemDAO(db.getmongodb(), db.getmorphia(), db.getDbName());
		
		ClothingItem item1 = new ClothingItem(cType, fType, "sexy tight", true);
		ClothingItem item2 = new ClothingItem(cType2, fType, "Thigh high stripped ", true);
		ClothingItem item3 = new ClothingItem(cType, fType2, "pin stripe", true);		
		
		clothingDAO.save(item1);
		clothingDAO.save(item2);
		clothingDAO.save(item3);
		
		logger.debug("Number of clothing items = {}",clothingDAO.count());
		
		//item3.setOwner(u);
		
		db.getDatastore().save(item1);
		db.getDatastore().save(item2);
		db.getDatastore().save(item3);
		
		ClothesBin bin =  new ClothesBin();
		
		bin.addClothes(item1);
		bin.addClothes(item2);
		bin.addClothes(item3);
		db.getDatastore().save(bin);
		
		db.getDatastore().save(u);
		
		u.setBin(bin);
		db.getDatastore().save(u);
		
		
		List< User > listOfUsers;
		
		listOfUsers = db.getDatastore().find( User.class, "userName =", "bacon").asList();
		
		
		for( User aUser : listOfUsers )		
		{		
			logger.debug("User Name = {}, Full Name = {}", aUser.getUserName(),aUser.getFullName());		
		}
		
		ClothesBinDAO clothesBins = new ClothesBinDAO(db.getmongodb(), db.getmorphia(),db.getDbName());
		
		logger.debug("Number of clothing items = {}",clothesBins.count());
		
		for(ClothesBin aBin: clothesBins.find().asList())
		{
		
			for(ClothingItem anItem:aBin.getClothingItems())
			{
				logger.debug("Clothing Item Description = {}",anItem.getDescription());
			}
		}
				
		
		Location aLocation = new Location("72 Jarlan", "Ottawa", "Ontario", "K2L 3K9", "Canada");
		Location aLocation2 = new Location("Town road", "Morristown", "Nova Scotia", "L2K 3V9", "Canada");
		
		Trip aTrip1 = new Trip(u,aLocation,new Date(), aLocation2, new Date());
		
		u.addTrip(aTrip1);
		
		db.getDatastore().save(u);
		
		TripDAO tripDao = new TripDAO(db.getmongodb(), db.getmorphia(), db.getDbName());
		
		tripDao.save(aTrip1);
		tripDao.find();
		
		
		for(Trip aTrip: tripDao.find().asList())
		{
			//logger.debug("Trip details Origin = {}, Destination = {}", aTrip.getOrigin().toString(),aTrip.getDestination().toString());
			
		}

	}

}
