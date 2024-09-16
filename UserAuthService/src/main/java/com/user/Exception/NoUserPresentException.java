package com.user.Exception;

public class NoUserPresentException extends Exception
{
	String message;
	public NoUserPresentException()
	{
		super("No user present in the database !!");
	}
	
	public NoUserPresentException(String message)
	{
		super(message);
		this.message=message;
	}
}
