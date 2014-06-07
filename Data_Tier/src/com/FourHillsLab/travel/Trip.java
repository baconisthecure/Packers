package com.FourHillsLab.travel;

import java.awt.PageAttributes.OriginType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Reference;
@Entity
public class Trip {
	private static final Logger logger = LoggerFactory.getLogger(Trip.class);
	@Id  private ObjectId    id = new ObjectId();
	
	@Reference User owner;
	//@Embedded List<User> travelers = new ArrayList<User>();
	//@Embedded ClothesBin packingList = new ClothesBin();
	@Embedded ArrayList<Location> tripRoute = new ArrayList<Location>(); 
	Date departureDate;
	Date returnDate;
	
	public Trip()
	{
		
	}
	
	public Trip(Location origin ,Date departureDate, Location destination , Date returnDate) {
		super();
		//this.owner = owner;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		tripRoute.add(origin);
		tripRoute.add(destination);		
	}	

	
	public Trip(User owner, Location origin ,Date departureDate, Location destination , Date returnDate) {
		super();
		//this.owner = owner;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		tripRoute.add(origin);
		tripRoute.add(destination);		
	}	

	
	/*public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<User> getTravelers() {
		return travelers;
	}
	public void setTravelers(List<User> travelers) {
		this.travelers = travelers;
	}
	public ClothesBin getPackingList() {
		return packingList;
	}
	public void setPackingList(ClothesBin packingList) {
		this.packingList = packingList;
	}
	*/
	public ArrayList<Location> getTripRoute() {
		return tripRoute;
	}
	public void setTripRoute(ArrayList<Location> tripRoute) {
		this.tripRoute = tripRoute;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Location getOrigin()
	{
		return tripRoute.get(0);
	}
	

	
	public Location getDestination()
	{
		return tripRoute.get(tripRoute.size() - 1);
	}
	
	

	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("Leaving ");
		builder.append(tripRoute.get(0).toString());
		builder.append(" at ");
		builder.append(departureDate);
		builder.append("and retrurning from ");
		builder.append(tripRoute.get(1).toString());
		builder.append(" at ");
		builder.append(returnDate);
		
		return builder.toString();
		
		
		
	}
	

}
