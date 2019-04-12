 package com.blute.att.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blute.att.domain.User;
import com.blute.att.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserResource {

	@Autowired
	private UserService userService;
	
	/**
	 * get all user for admin
	 * @return
	 */
	@GetMapping(value = "/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	/**
	 * get the user for the current user
	 * @param principal
	 * @return
	 */
	@GetMapping(value = "/getuser")
	@PreAuthorize("hasRole('USER')")
	
	public ResponseEntity<User> getUser(Principal principal){		
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	// TODO: edit employee details 
	
	//TODO: get particular employee for admin based on username/email.
	
	
}
