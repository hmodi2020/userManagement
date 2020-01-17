package com.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.model.Tag;
import com.usermanagement.repository.TagRepository;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public Tag createTag(Tag tag) {
		return tagRepository.save(tag);
	}

	public List<Tag> getAllTag() {
		return tagRepository.findAll();
	}
}
