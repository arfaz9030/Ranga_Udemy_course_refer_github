package com.example.demo;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.User.User;
import com.example.demo.User.UserNotFoundException;
import com.example.demo.dao.service.layer.UserDaoService;

@RestController
public class UserResourceakaController {

	
	private UserDaoService service; 
	
//	private UserDaoService service= new UserDaoService(); we can define as below to create object of user as this line do same
	
	public UserResourceakaController(UserDaoService service) {
		this.service = service;
	}
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	@GetMapping("/users/{id}")
	public User retrieveAUser(@PathVariable int id) {
		User user1 =  service.findFirstOne(id);
		if(user1==null)				{
			System.out.println("enter user=null");
			throw new UserNotFoundException("id: "+id);
		}	
				
		return user1;
	}
//	@PostMapping("/users/add")
//	public User saveUser(@RequestBody User useradd)  {
//		return service.save(useradd);
//	}

	// Enhancing above post method adding response entity to add  HTTP Status Code and Location URI	
	@PostMapping("/users/add")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User useradd)  {
		User savedUser= service.save(useradd);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
		
	}
}
