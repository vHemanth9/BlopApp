package com.springboot.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String Email);
	
	Optional<User> findByUsernameOrEmail(String username,String Email);

    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String Email);
}

