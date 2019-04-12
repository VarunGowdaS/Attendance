package com.blute.att.respository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blute.att.domain.User;

/**
 * Spring Data JPA repository  for User entity
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findOneByEmailIgnoreCase(String email);

    //Optional<User> findOneByLogin(String login);

	//Page<User> findAllByLoginNot(Pageable pageable, String login);

	User findByEmailIgnoreCase(String username);

	
}

