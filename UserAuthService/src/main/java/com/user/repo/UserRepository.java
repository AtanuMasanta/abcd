package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;
import com.user.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>
{
	@Query("select u from User u where u.email = :email")
	public Optional<User> getUserByUserName(@Param("email") String email);
}
