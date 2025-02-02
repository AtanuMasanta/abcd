package com.user.dto;

public class UserDto 
{
	private int id;
	private String name;
	private String email;
	private String location;
	private String role;
	
	public UserDto() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int id, String name, String email, String location, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.location = location;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
