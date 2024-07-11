package com.rodrigueshelryson.workshopmongo.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigueshelryson.workshopmongo.domain.Post;
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
	
	public boolean contemPalavraOfensiva(String conteudo) {
		
		String path = "C:\\temp\\offensive.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String palavra = br.readLine();
			while(palavra != null) {
				if(conteudo.toLowerCase().contains(palavra)) {
					return true;
				}
				palavra = br.readLine();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User verificaPosts(String id) {
		User obj = findById(id);
		for(Post post : obj.getPosts()) {
			if(contemPalavraOfensiva(post.getBody())) {
				obj.removePosts(post);
			}
		}
		return obj;
	}
	
}
