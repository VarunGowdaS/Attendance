package com.blute.att.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blute.att.domain.Device;
import com.blute.att.domain.User;
import com.blute.att.respository.UserRepository;
import com.blute.att.rest.errors.EmailAlreadyUsedException;
//import com.blute.att.service.DeviceService;
import com.blute.att.service.UserService;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * @Autowired private DeviceService deviceService;
	 */
	
	@PostMapping(value = "/registration")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> registration(@RequestBody User user) throws URISyntaxException{
		//TODO :: ADD LOGGER
		
		User dbUser = userService.save(user);
		if(dbUser != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return null;
	}

	
	/*@PutMapping(value = "/updateuser")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		//TODO :: ADD LOGGER
		
		Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
		if(existingUser.isPresent() && (!existingUser.get().getId().equals(user.getId()))) {
			throw new EmailAlreadyUsedException();
		}
		Optional<User> updateUser = userRepository.save(user);
		return null;
		
		
	}*/
	
}
