package com.FourHillsLab.utilities;

import org.bson.types.ObjectId;


import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.*;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;

@Entity
public class FormalityType 
{
	
	@Id private ObjectId id = new ObjectId();
	Boolean isFancy = false;
	Boolean isCasual = false;
	Boolean isBusiness = false;
	Boolean isExercise = false;
	
	
	
	public Boolean getIsFancyEvent()
	{
		return isFancy;	
	}

	public Boolean getIsCasuaEvent()
	{
		return isCasual;	
	}
	
	public Boolean getIsBusinessEvent()
	{
		return isBusiness;	
	}
	
	public Boolean getIsExerciseEvent()
	{
		return isExercise;	
	}

	
	
	
	public void setIsFancyEvent(Boolean isIt)
	{
		isFancy = isIt;	
	}

	public void setIsCasuaEvent(Boolean isIt)
	{
		isCasual = isIt;		
	}
	
	public void setIsBusinessEvent(Boolean isIt)
	{
		isBusiness = isIt;	
	}
	
	public void setIsExerciseEvent(Boolean isIt)
	{
		isExercise = isIt;	
	}
	
}
