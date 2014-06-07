package com.FourHillsLab.utilities.Exceptions;

public class NotFoundException extends Exception {
	
	public NotFoundException()
	{
		super("Unable to find object");
	}
	
	public NotFoundException(String objectName)
	{
		super("Unable to find object:" + objectName );
	}
	
	

}
