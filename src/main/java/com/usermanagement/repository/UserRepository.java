package com.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	List<User> findAllByFirstName(String firstname);
	Optional<User> findByEmail(String email);


}
