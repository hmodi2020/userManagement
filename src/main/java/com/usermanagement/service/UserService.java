package com.usermanagement.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.exceptions.DuplicateDataException;
import com.usermanagement.modle.User;
import com.usermanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
	User existsUser=userRepository.findByEmail(user.getEmail());
	if(Objects.nonNull(existsUser)) {
		throw new DuplicateDataException("email id already exists");
	}
		userRepository.save(user);
		return user;
	}
	
	public List<User> getUsers(String firstName){
		return Objects.isNull(firstName) ?  userRepository.findAll() : userRepository.findAllByFirstName(firstName);
	}
	
	public User getUserById(Long userid) {
		return userRepository.findById(userid).get();
	}
	
	
	public User updateUser(Long userid,User user) {
		User existsUser=userRepository.findById(userid).get();
		if(Objects.nonNull(existsUser)) {
			existsUser.setFirstName(user.getFirstName());
			userRepository.save(existsUser);
		}
		return existsUser;
	}
	
	public boolean deleteUserById(Long userid) {
		userRepository.deleteById(userid);
		return true;
	}
	
};
