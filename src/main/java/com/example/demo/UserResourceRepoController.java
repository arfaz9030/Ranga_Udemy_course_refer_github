package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.User.Post;
import com.example.demo.User.User;
import com.example.demo.User.UserNotFoundException;
import com.example.demo.dao.service.layer.UserDaoService;
import com.example.demo.jparepository.UserRepo;

@RestController
public class UserResourceRepoController {

	
	private UserDaoService service; 
	private UserRepo repository; 	
//	we can define as below to create object of service and Repository(by @springbootapplication scanning) as private UserDaoService service= new UserDaoService(); do same
	
	public UserResourceRepoController(UserDaoService service, UserRepo repository) {
		this.service = service;
		this.repository = repository;
	}
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return this.repository.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveAUser(@PathVariable int id) {
		Optional<User> user1 =  repository.findById(id);
		if(user1.isEmpty())				{
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
	@PostMapping("/jpa/users/add")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User useradd)  {
		User savedUser= repository.save(useradd);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
		
	}

}
