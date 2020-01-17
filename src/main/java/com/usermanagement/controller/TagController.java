package com.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.ResponseHandler;
import com.usermanagement.model.Tag;
import com.usermanagement.model.User;
import com.usermanagement.service.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tag")
@Api("Tag apis")
public class TagController {

	@Autowired
	private TagService tagService;

	@ApiOperation(value = "View a list of Tag", response = ResponseEntity.class)
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("/")
	public ResponseEntity<ResponseHandler> getAllTag() {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", tagService.getAllTag()),
				HttpStatus.OK);
	}

	/**
	 * add new tag
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "create new Tag ", response = ResponseEntity.class)
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping("/")
	public ResponseEntity<?> AddUserDetails(@Valid @RequestBody Tag tag) {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.CREATED, LocalDateTime.now(), "success", tagService.createTag(tag)),
				HttpStatus.OK);
	}

}
