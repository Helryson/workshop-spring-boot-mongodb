package com.rodrigueshelryson.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigueshelryson.workshopmongo.domain.Post;
import com.rodrigueshelryson.workshopmongo.services.PostServices;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {
	
	@Autowired
	private PostServices postServices;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = postServices.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
