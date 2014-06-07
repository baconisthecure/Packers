package com.FourHillsLab.travel;



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



@Embedded
public class Location {

    


	private String    street;
    private String    city;
    private String    state;
    private String    zipcode;
    private String    country;
    private long 	  longitude;
    private long 	  latitude;

    public Location()
    {
    	
    }
    
    
    public Location(String street, String city, String state, String zipcode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}
    
    public Location(String street, String city, String state, String zipcode, String country, long longitude, long latitude) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
	}
    
        
    public String getStreet() { return this.street; }
    public void setStreet( String street ) { this.street = street; }
    public String getCity() { return this.city; }
    public void setCity( String city ) { this.city = city; }
    public String getState() { return this.state; }
    public void setState( String state ) { this.state = state; }
    public String getZipcode() { return this.zipcode; }
    public void setZipcode( String zipcode ) { this.zipcode = zipcode; }
    public String getCountry() { return this.country; }
    public void setCountry( String country ) { this.country = country; }
    public long getLongitude() { return longitude;	}
	public void setLongitude(long longitude) {this.longitude = longitude;	}
	public long getLatitude() {	return latitude;	}
	public void setLatitude(long latitude) { this.latitude = latitude;}


	public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(this.street).append(", ").append(city).append(", ").append(state).append(", ").append(country).append(", ").append(zipcode);
        return builder.toString();
    }
}