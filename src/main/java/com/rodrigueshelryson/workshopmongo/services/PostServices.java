package com.rodrigueshelryson.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigueshelryson.workshopmongo.domain.Post;
import com.rodrigueshelryson.workshopmongo.repository.PostRepository;
import com.rodrigueshelryson.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostServices {

	@Autowired
	private PostRepository userRepository;

	public List<Post> findAll() {
		return userRepository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
}
