package com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import com.user.Exception.InvalidataException;
import com.user.Exception.UserNotFoundException;
import com.user.controller.UserController;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.repo.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Logger logger=LoggerFactory.getLogger(UserService.class);
	
	public UserDto addUser(User u) throws InvalidataException 
	{
		try
		{
			u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
			User user= this.userRepository.save(u);
			UserDto userDto=convertUserToUserDto(user);
			return userDto;
		}
		catch(Exception e)
		{
			logger.error("Some error has occured with database ,data is not inserted into database !!");
			throw new InvalidataException(e.getMessage());
		}
		
	}
	public UserDto getUserById(int id) throws UserNotFoundException
	{
		Optional<User>user=this.userRepository.findById(id);
		if(user.isEmpty())
		{
			logger.info("no user found with the id "+id);
			throw new UserNotFoundException("user is not found with given id :"+id);
		}
		
		else
		{
			UserDto userDto=convertUserToUserDto(user.get());
			return userDto;
		}
	}
	
	public List<UserDto> getAllUser()
	{
		//return this.userRepository.findAll();
		return userRepository.findAll()
                .stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
	}
	
	public String DeleteUser(int userid) throws UserNotFoundException
	{
		Optional<User> user=this.userRepository.findById(userid);
		if(user.isEmpty())
		{
			logger.info("delete operation can't be performed as no user found with the id "+userid);
			throw new UserNotFoundException("user is not found with given id :"+userid);
		}
		else
		{
			User u=user.get();
			this.userRepository.delete(u);
		}
		return "User deleted successfully !!";
	}
	
	public UserDto convertUserToUserDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setLocation(user.getLocation());
		userDto.setName(user.getName());
		userDto.setRole(user.getRole());
		return userDto;
		
	}
}
