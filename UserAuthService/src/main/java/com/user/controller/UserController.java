package com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import com.user.Exception.InvalidCredential;
import com.user.Exception.InvalidataException;
import com.user.Exception.NoUserPresentException;
import com.user.Exception.UserNotFoundException;
import com.user.dto.AuthenticationRequest;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.service.AuthenticationService;
import com.user.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController 
{
	@Autowired
	private UserService userService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthenticationService authenticationService; 
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody User user,BindingResult result) throws InvalidataException
	{
		if(result.hasErrors())
		{
			logger.error("User has enter wrong data while trying to add data in data base !!");
			throw new InvalidataException("your data is matching with policies !!");
		}
		UserDto userDto=this.userService.addUser(user);
		logger.info("User added to DataBase Successfully !!");
		return ResponseEntity.ok(userDto);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody User user,BindingResult result) throws InvalidataException
	{
		if(result.hasErrors())
		{
			logger.error("User has enter wrong data while trying to modify data !!");
			throw new InvalidataException();
		}
		UserDto userDto=this.userService.addUser(user);
		logger.info("User data is modified successfully in data base!!");
		return ResponseEntity.ok(userDto);
		
	}
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id")int id) throws UserNotFoundException
	{
		logger.info("Fetch the user with the user id {} successfully",id);
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	@GetMapping("/gettAllUser")
	public ResponseEntity<List<UserDto>> getAll() throws NoUserPresentException
	{
		System.out.print("hello\n");
		List<UserDto>userList=this.userService.getAllUser();
		if(userList.size()==0)
		{
			logger.info("No data present at this moment in database !!");
			throw new NoUserPresentException();
		}
		logger.info("Fetch data from database successfully  !!");
		return ResponseEntity.ok(userList);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id")int id) throws UserNotFoundException
	{
		logger.info("User deleted successfully !!");
		return ResponseEntity.ok(userService.DeleteUser(id));
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody AuthenticationRequest authRequest) throws InvalidCredential
	{
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) 
		{
			logger.info("User authenticated successfully ,User id is :"+authRequest.getUsername());
			logger.info("Jwt token generated !!");
	         return authenticationService.generateToken(authRequest.getUsername());
	  
	     } 
		 else 
		 {
	          throw new InvalidCredential("Your credentials is not correct !! please enter correct credential");
	     }
	}
	
	@GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
		authenticationService.validateToken(token);
		logger.info("Token validated successfully !!");
        return "Token is valid";
    }
	
	
	
	
	
	
	
	
}
