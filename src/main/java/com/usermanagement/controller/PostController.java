package com.usermanagement.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.model.Answer;
import com.usermanagement.model.Comment;
import com.usermanagement.model.ResponseHandler;
import com.usermanagement.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/post")
@Api("Post apis")
public class PostController {

	@Autowired
	private PostService postService;
	
	
@ApiOperation(value = "View a list of al User and user by name",response = ResponseEntity.class)
@PreAuthorize("hasAnyRole('USER')")
@GetMapping("/")
	public ResponseEntity<ResponseHandler> getAllPost() {
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.OK, LocalDateTime.now(), "success", postService.finaAllPost()),
				HttpStatus.OK);
	}
	

	
	@PostMapping("/{id}/tag/{tag_id}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> addTagInPost(@PathVariable Long id,@PathVariable(name = "tag_id") Long tagId){
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.CREATED, LocalDateTime.now(), "success",
					postService.addTagInPost(id, tagId)	),
				HttpStatus.OK);
	}
	
	@PostMapping("/{id}/user/{user_id}/answer")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> addAnswerInPost(@PathVariable Long id,@PathVariable(name = "user_id") Long userId,@RequestBody Answer answer){
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.CREATED, LocalDateTime.now(), "success",
					postService.addAnswerInPost(id, userId, answer)),
				HttpStatus.OK);
	}
	
	@PostMapping("/{id}/user/{user_id}/comment")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<?> addCommentInPost(@PathVariable Long id,@PathVariable(name = "user_id") Long userId,@RequestBody Comment comment){
		return new ResponseEntity<ResponseHandler>(
				new ResponseHandler(HttpStatus.CREATED, LocalDateTime.now(), "success",
					postService.addCommentsInPost(id, userId, comment)	),
				HttpStatus.OK);
	}
	
}
