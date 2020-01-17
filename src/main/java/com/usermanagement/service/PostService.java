package com.usermanagement.service;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.exceptions.DataNotFoundException;
import com.usermanagement.model.Answer;
import com.usermanagement.model.Comment;
import com.usermanagement.model.Post;
import com.usermanagement.model.Tag;
import com.usermanagement.model.User;
import com.usermanagement.repository.PostRepository;
import com.usermanagement.repository.TagRepository;
import com.usermanagement.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Post addTagInPost(Long postId,Long tagId) {
		Optional<Tag> tag=tagRepository.findById(tagId);
		if(!tag.isPresent()){
			throw new DataNotFoundException("tag not found");
		}
		Optional<Post> post=postRepository.findById(postId);
		post.get().getTags().add(tag.get());
		tag.get().getPosts().add(post.get());
		postRepository.save(post.get());
		return post.get();
	}
	
	public Post addAnswerInPost(Long postId,Long userId,Answer answer) {
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent()){
			throw new DataNotFoundException("tag not found");
		}
		answer.setUser(user.get());
		Optional<Post> post=postRepository.findById(postId);
		post.get().getAnswers().add(answer);
		postRepository.save(post.get());
		return post.get();
	}
	
	public Post addCommentsInPost(Long postId,Long userId,Comment comment) {
		Optional<User> user=userRepository.findById(userId);
		if(!user.isPresent()){
			throw new DataNotFoundException("tag not found");
		}
		comment.setUser(user.get());
		Optional<Post> post=postRepository.findById(postId);
		post.get().getComments().add(comment);
		postRepository.save(post.get());
		return post.get();
	}
	
	public List<Post> finaAllPost(){
		return postRepository.findAll();
	}
}
