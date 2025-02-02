package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService
{
	@Autowired
	private JwtService jwtService;
	
	public String generateToken(String username) 
	{
        return jwtService.generateToken(username);
    }

    public void validateToken(String token)
    {
        jwtService.validateToken(token);
    }

}
