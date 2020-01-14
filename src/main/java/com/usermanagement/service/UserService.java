package com.usermanagement.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usermanagement.exceptions.DataNotFoundException;
import com.usermanagement.exceptions.DuplicateDataException;
import com.usermanagement.model.Role;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.repository.RoleRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public User createUser(User user) {
		Optional<User> existsUser = userRepository.findByEmail(user.getEmail());
		if(existsUser.isPresent()) {
			throw new DuplicateDataException("email id already exists")	;
		}
		Role role =roleRepository.findByRole("ADMIN");
		LOGGER.info(role.getRole() +  "--" );
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		LOGGER.info("user add successfully");
		return user;
	}

	public List<User> getUsers(String firstName) {
		List<User> users = Objects.isNull(firstName) ? userRepository.findAll()
				: userRepository.findAllByFirstName(firstName);
		if (users.isEmpty()) {
			throw new DataNotFoundException("user not found");
		}
		return users;
	}

	public User getUserById(Long userid) {
		Optional<User> user = userRepository.findById(userid);
		if (!user.isPresent()) {
			throw new DataNotFoundException("user not found");
		}
		return user.get();
	}

	public User updateUser(Long userid, User user) {
		User existsUser = userRepository.findById(userid).get();
		if (Objects.isNull(existsUser)) {
			throw new DataNotFoundException("user not found");
		} else {
			existsUser.setFirstName(user.getFirstName());
			userRepository.save(existsUser);
		}
		LOGGER.info("user update successfully");
		return existsUser;
	}

	public boolean deleteUserById(Long userid) {
		userRepository.deleteById(userid);
		return true;
	}

};
