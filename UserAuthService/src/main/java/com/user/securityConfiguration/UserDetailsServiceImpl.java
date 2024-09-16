package com.user.securityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import com.user.entity.User;
import com.user.repo.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<User> user=this.userRepository.getUserByUserName(username);
		if(user.isEmpty())
		{
			throw new UsernameNotFoundException("couldn't found user by the email :"+username);
		}
		User u=user.get();
		CustomUserDetails customUserDetails=new CustomUserDetails(u);
		return customUserDetails;
	}

}
