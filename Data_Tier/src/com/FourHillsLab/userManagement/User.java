package com.FourHillsLab.userManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Currency;
import java.util.Locale;

import org.bson.BSONObject;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.FourHillsLab.travel.Trip;
import com.FourHillsLab.wardrobe.ClothesBin;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.annotations.Reference;

import org.bson.types.ObjectId;
import org.hamcrest.core.IsAnything;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Entity
public class User  {	
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	@Id ObjectId _id = new ObjectId();
	private String userName  = "";;
	private String fullName = "";;
	private String firstName = "";;
	private String middleName = "";;
	private String lastName = "";;
	private String sex = "";;
	private String email = "";;
	private String twitter = "";;
	private Date birthday;
	//private Currency localCurrency;
	private Locale homeLocale;
	private String passportNumber = "";
	private Date passportExpiration = null;	
	//private TimeZone  currentTimeZone = null;
	private Date userCreatedOn = null;
	private Boolean activeUser = false;	
	@Embedded private ClothesBin bin = new ClothesBin();
	@Embedded private ArrayList<Trip> trips = new ArrayList<Trip>();
	
	

	User()
	{ 
		
	}
	
	public User(String uName)
	{		
		logger.debug("Construct User Name = {}", uName);
		userName = uName;		
		userCreatedOn = new Date();
	}
	
	public User(String uName, String emailAddress)
	{
		logger.debug("Construct User Name = {}, Email = {}",uName,emailAddress);
		email = emailAddress;
		userName = uName;
		userCreatedOn = new Date();
	}
	
	public User(String uName, String emailAddress, String fn, String ln)
	{
		logger.debug("Construct User Name = {}, Email = {}",uName,emailAddress);
		
		userName = uName;
		email = emailAddress;
		firstName = fn;
		lastName = ln;
		SetFullName(fn,ln);
		userCreatedOn = new Date();
		logger.debug("Full name = {}", fullName);
		
	
	}
	
	
	public User(String uName, String emailAddress, String fn, String mn, String ln)
	{
		logger.debug("Construct User Name = {}, Email = {}",uName,emailAddress);		
		
		userName = uName;
		email = emailAddress;
		firstName = fn;
		middleName = mn;
		lastName = ln;
		
		SetFullName(fn,mn,ln);
		userCreatedOn = new Date();
		logger.debug("Full name = {}", fullName);
		
	
	}
	
	public void SetFullName(String fn, String ln)
	{
		fullName = fn + " " + ln;
	}

	public void SetFullName(String fn, String mn, String ln)
	{
		fullName = fn + " " + mn + " " + ln;
	}
	
	public void setSex(String newSex)
	{
		sex = newSex;
	}
	
	
	public void setEmail( String newEmail)
	{
		email = newEmail;
	}

	
	public void setTwitter(String twitterID)
	{
		twitter = twitterID;
	}
	
	
	public void setBirthday(Date day)
	{
		birthday = day;
	}
	

	
	public void setHomeLocale(Locale locale)
	{
		homeLocale = locale;
	}
	
	
	public void setPassport(String passNumber)
	{
		passportNumber = passNumber;
	}
	
	public void setPassport(String passNumber, Date expireDate)
	{
		passportNumber = passNumber;
		passportExpiration = expireDate;
	}	
	
	

		
	public void setUsername(String uname)
	{
		userName = uname;
	}
	
	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}
	
	public void setActive(Boolean isActive)
	{
		activeUser = isActive;
	}
	
	public void set_Id(ObjectId id) {
		this._id = id;
	}




	public ObjectId get_Id() {
		return _id;
	}


	public ClothesBin getBin() {
		return bin;
	}

	public void setBin(ClothesBin bin) {
		this.bin = bin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Date getPassportExpiration() {
		return passportExpiration;
	}

	public void setPassportExpiration(Date passportExpiration) {
		this.passportExpiration = passportExpiration;
	}

	public Date getUserCreatedOn() {
		return userCreatedOn;
	}

	public Boolean getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Boolean activeUser) {
		this.activeUser = activeUser;
	}

	public String getSex() {
		return sex;
	}

	public String getEmail() {
		return email;
	}

	public String getTwitter() {
		return twitter;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Locale getHomeLocale() {
		return homeLocale;
	}
	
	public ArrayList<Trip> getTrips() {
		return trips;
	}

	public  void setTrips(ArrayList<Trip> tripList) {
		trips = tripList;
	}


	public void addTrip(Trip aTrip)
	{	
		
		trips.add(aTrip);
		logger.debug("Added trip {}", aTrip.toString());
	}
	
	public String toString()
	{
		String activeString = "Inactive";
		if(activeUser)
		{
			activeString = "Active";
		}
		
		String userString = String.format("%s user with full name %s created on %s", activeString, fullName, userCreatedOn.toString());
		
		return userString;
	}


}
