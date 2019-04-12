package com.blute.att.service;

import java.util.List;

import com.blute.att.domain.User;



public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);

}
