package com.rodrigueshelryson.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigueshelryson.workshopmongo.domain.User;
import com.rodrigueshelryson.workshopmongo.dto.UserDTO;
import com.rodrigueshelryson.workshopmongo.repository.UserRepository;
import com.rodrigueshelryson.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User newObj) {
		User obj = findById(newObj.getId());
		updateData(newObj, obj);
		return userRepository.save(obj);
	}
	
	public void updateData(User newObj, User obj){
		obj.setName(newObj.getName());
		obj.setEmail(newObj.getEmail());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
