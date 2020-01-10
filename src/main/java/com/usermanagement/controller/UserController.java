package com.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.exceptions.DuplicateDataException;
import com.usermanagement.modle.ResponseHandler;
import com.usermanagement.modle.User;
import com.usermanagement.service.AdvanceValidation;
import com.usermanagement.service.BasicValidation;
import com.usermanagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Validator validator;

	@GetMapping("/")
	public ResponseEntity<ResponseHandler> getAllUserDetails(@RequestParam(required = false) String firstName) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.getUsers(firstName)),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHandler> getUserDetailsById(@PathVariable Long id) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.getUserById(id)),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> AddUserDetails(@Valid @RequestBody User user) {
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user, BasicValidation.class);
		if (constraintViolations.size() > 0) {
			Set<String> list = constraintViolations.stream().map(k -> k.getMessage()).collect(Collectors.toSet());
			return new ResponseEntity<ResponseHandler>(
					new ResponseHandler(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "error", list),
					HttpStatus.BAD_REQUEST);
		}
				return new ResponseEntity<ResponseHandler>(
						new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success",
								userService.createUser(user)),
						HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @Valid @RequestBody User user) {
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user, AdvanceValidation.class);
		if (constraintViolations.size() > 0) {
			Set<String> list = constraintViolations.stream().map(k -> k.getMessage()).collect(Collectors.toSet());
			return new ResponseEntity<ResponseHandler>(
					new ResponseHandler(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "error", list),
					HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ResponseHandler>(new ResponseHandler(HttpStatus.OK, LocalDateTime.now(),
					"success", userService.updateUser(id, user)), HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.deleteUserById(id)),
				HttpStatus.OK);

	}

}
