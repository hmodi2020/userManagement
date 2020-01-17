package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.model.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{

}
