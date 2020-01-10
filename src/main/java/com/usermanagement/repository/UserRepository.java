package com.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.modle.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	List<User> findAllByFirstName(String firstname);
	User findByEmail(String email);


}
