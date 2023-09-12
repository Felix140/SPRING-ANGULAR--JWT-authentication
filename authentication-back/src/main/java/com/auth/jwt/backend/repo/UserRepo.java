package com.auth.jwt.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.jwt.backend.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByLogin(String login);

}
