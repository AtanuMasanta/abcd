package com.user.Exception;

public class InvalidataException extends Exception
{
	String message;
	public InvalidataException()
	{
		super("you have entered wrong data");
	}
	public InvalidataException(String message)
	{
		super(message);
		this.message=message;
		
	}
	

}
