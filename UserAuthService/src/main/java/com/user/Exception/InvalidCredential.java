package com.user.Exception;

public class InvalidCredential extends Exception
{
	String message;
	
	public InvalidCredential()
	{
		super("your credential are not correct !!");
	}
	
	public InvalidCredential(String message)
	{
		super(message);
		this.message=message;
	}
	
}
