package com.user.Exception;

public class UserNotFoundException extends Exception
{
	private String message;
	
	public UserNotFoundException()
	{
		super("user is not found with the given id !!");
	}
	public UserNotFoundException(String message)
	{
		super(message);
		this.message=message;
	}
}
