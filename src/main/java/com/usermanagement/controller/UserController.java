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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.Post;
import com.usermanagement.model.ResponseHandler;
import com.usermanagement.model.User;
import com.usermanagement.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("user management system")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Validator validator;

	/**
	 * 
	 * @param firstName
	 * @return
	 */
	
@ApiOperation(value = "View a list of al User and user by name",response = ResponseEntity.class)
@PreAuthorize("hasAnyRole('USER')")
@GetMapping("/")
	public ResponseEntity<ResponseHandler> getAllUserDetails(@RequestParam(required = false) String firstName) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.getUsers(firstName)),
				HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "View a list of user by id",response = ResponseEntity.class)
	@GetMapping("/{id}")
	public ResponseEntity<ResponseHandler> getUserDetailsById(@PathVariable Long id) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.getUserById(id)),
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "create new user ",response = ResponseEntity.class)
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping("/")
	public ResponseEntity<?> AddUserDetails(@Valid @RequestBody User user) {
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user, User.BasicValidation.class);
		if (constraintViolations.size() > 0) {
			Set<String> list = constraintViolations.stream().map(k -> k.getMessage()).collect(Collectors.toSet());
			return new ResponseEntity<ResponseHandler>(
					new ResponseHandler(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "error", list),
					HttpStatus.BAD_REQUEST);
		}
				return new ResponseEntity<ResponseHandler>(
						new ResponseHandler(HttpStatus.CREATED, LocalDateTime.now(), "success",
								userService.createUser(user)),
						HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "update user",response = ResponseEntity.class)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserDetails(@PathVariable Long id, @Valid @RequestBody User user) {
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user, User.AdvanceValidation.class);
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

	/**
	 * delete 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "delete user by id",response = ResponseEntity.class)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.deleteUserById(id)),
				HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "add/create post by user")
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping("/{id}/post")
	public ResponseEntity<?> createPostByUser(@PathVariable Long id,@RequestBody Post post){
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", userService.addPostByUser(id, post)),
				HttpStatus.OK);

	}

}
