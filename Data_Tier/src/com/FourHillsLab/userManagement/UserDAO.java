package com.FourHillsLab.userManagement;


import java.util.List;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;





import com.FourHillsLab.utilities.Exceptions.NotFoundException;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.dao.BasicDAO;
import com.github.jmkgreen.morphia.query.QueryResults;
import com.mongodb.Mongo;

public class UserDAO extends BasicDAO<User, ObjectId> 
{
	
	public UserDAO(Mongo mongo,Morphia morphia,String dbName)
	{
		super( mongo, morphia, dbName );	
		
	}
	
	
	
	
	public List<User> findByUserName( String userName ) {
	    
	    return ds.find(User.class).filter("userName =", userName).order("userName").asList();
	    
	    
	    //Using Regular Expressions
	    //Pattern regExp = Pattern.compile(userName + ".*", Pattern.CASE_INSENSITIVE);
	    //return ds.find(User.class).filter("userName", regExp).order("userName").asList();
	}
	
	public User findById(String id) throws NotFoundException {
	    if (!ObjectId.isValid(id)) {
	        throw new NotFoundException();
	    }

	    ObjectId oid = new ObjectId(id);
	    User m = ds.find(User.class).field("id").equal(oid).get();
	    if (m == null) {
	        throw new NotFoundException();
	    }
	    return m;
	}

	
	
	public List<User> findAllUsers() {
	    
	    return ds.find(User.class).order("userName").asList();
	    
	    
	    //Using Regular Expressions
	    //Pattern regExp = Pattern.compile(userName + ".*", Pattern.CASE_INSENSITIVE);
	    //return ds.find(User.class).filter("userName", regExp).order("userName").asList();
	}

}	
