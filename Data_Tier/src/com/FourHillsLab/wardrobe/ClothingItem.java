package com.FourHillsLab.wardrobe;


import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import org.bson.BSONObject;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.*;
import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.utilities.FormalityType;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.annotations.Reference;

import org.bson.types.ObjectId;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.BSONObject;




@Entity
public class ClothingItem 

{
	
	
	@Id private ObjectId id = new ObjectId();
	
	
	private String m_description;
	@Embedded private ClothingType m_type;
	//@Reference private User m_owner;  
	@Embedded private FormalityType m_clothingType;
		
	private Boolean m_activeItem;
	
	public ClothingItem()
	{
	
	}

	public ClothingItem(ClothingType m_type, FormalityType m_clothingType, String m_description, Boolean m_activeItem) {
		
		
		this.m_description = m_description;
		this.m_type = m_type;
		
		this.m_clothingType = m_clothingType;
		this.m_activeItem = m_activeItem;
	}



	public String getDescription() {
		return m_description;
	}

	public void setDescription(String m_description) {
		this.m_description = m_description;
	}

	public ClothingType getType() {
		return m_type;
	}

	public void setType(ClothingType m_type) {
		this.m_type = m_type;
	}

	/*public User getOwner() {
		return m_owner;
	}

	public void setOwner(User m_owner) {
		this.m_owner = m_owner;
	}*/

	public FormalityType getClothingType() {
		return m_clothingType;
	}

	public void setClothingType(FormalityType m_clothingType) {
		this.m_clothingType = m_clothingType;
	}

	public Boolean getActiveItem() {
		return m_activeItem;
	}

	public void setActiveItem(Boolean m_activeItem) {
		this.m_activeItem = m_activeItem;
	}
	




}
