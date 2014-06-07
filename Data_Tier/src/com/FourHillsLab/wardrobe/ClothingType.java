package com.FourHillsLab.wardrobe;
 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Currency;
import java.util.Locale;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.BSONObject;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.annotations.Reference;

import org.bson.types.ObjectId;

@Entity
public class ClothingType 
{
	@Id private ObjectId id = new ObjectId();
	String itemType;
	Boolean isActive;
	
	public void setItemType(String t)
	{
		itemType = t;
	}
	
	
	public String getItemType()
	{
		return itemType;
		
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
