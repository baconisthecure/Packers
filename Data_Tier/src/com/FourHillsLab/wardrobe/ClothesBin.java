package com.FourHillsLab.wardrobe;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.*;
import com.FourHillsLab.utilities.FormalityType;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.annotations.Reference;

import org.bson.types.ObjectId;


@Entity 
public class ClothesBin 
{
	@Id private ObjectId id = new ObjectId();
	@Embedded List<ClothingItem> m_wardrobe = new ArrayList<ClothingItem>();
	
	
	public void addClothes(ClothingItem item)
	{
		m_wardrobe.add(item);
	}
	
	
	public List<ClothingItem> getClothingItems()
	{
		return m_wardrobe;
	}
	
	

	
}
