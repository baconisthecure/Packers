package com.FourHillsLab.travel;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.FourHillsLab.userManagement.User;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.dao.BasicDAO;
import com.mongodb.Mongo;


public class TripDAO extends BasicDAO<Trip,ObjectId> {
	private static final Logger logger = LoggerFactory.getLogger(TripDAO.class);
	public TripDAO(Mongo mongo,Morphia morphia,String dbName)
	{
		super( mongo, morphia, dbName );	
		
	}
	
	
	public List<Trip> findByUserName( String userName ) {
		
		//find all the users first then go through the trips that those user have.
	    
	     List<User> users = ds.find(User.class).filter("userName =", userName).order("userName").asList();
	     List<Trip> returnTripList = new ArrayList<Trip>();
	     
	     for(User aUser: users)
	     {
	    	 returnTripList.addAll( aUser.getTrips());	
	    	 
	     }
	     logger.debug("Found {} users with userName {}", users.size(),userName);
	     logger.debug("Total of {} trips", returnTripList.size());
	     return returnTripList;
	    
	    
	    //Using Regular Expressions
	    //Pattern regExp = Pattern.compile(userName + ".*", Pattern.CASE_INSENSITIVE);
	    //return ds.find(User.class).filter("userName", regExp).order("userName").asList();
	}

}
