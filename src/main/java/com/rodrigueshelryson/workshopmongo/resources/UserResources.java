package com.rodrigueshelryson.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigueshelryson.workshopmongo.domain.entities.User;
import com.rodrigueshelryson.workshopmongo.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userServices.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
