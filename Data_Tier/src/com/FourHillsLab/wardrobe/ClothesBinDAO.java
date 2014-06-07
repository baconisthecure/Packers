package com.FourHillsLab.wardrobe;


import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Morphia;
import com.github.jmkgreen.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

public class ClothesBinDAO extends BasicDAO<ClothesBin, ObjectId> 
{
	public ClothesBinDAO(Mongo mongo,Morphia morphia,String dbName)
	{
		super( mongo, morphia, dbName );	
		
	}
	
	
}
