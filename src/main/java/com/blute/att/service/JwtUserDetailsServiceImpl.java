package com.blute.att.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blute.att.domain.User;
import com.blute.att.respository.JwtUserFactory;
import com.blute.att.respository.UserRepository;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailIgnoreCase(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username", username));
	
		}else {
			return JwtUserFactory.create(user);
		}
	}
	
	
				
	
}
