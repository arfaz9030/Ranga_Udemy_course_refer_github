package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.User.Post;
import com.example.demo.User.User;
import com.example.demo.User.UserNotFoundException;
import com.example.demo.jparepository.PostRepo;
import com.example.demo.jparepository.UserRepo;

@RestController
public class UserPostResourceRepoController {

	
	private UserRepo repository; 	
	
	private PostRepo postRepository; 	
//	we can define as below to create object of repository and postRepository(by @springbootapplication scanning) as private UserDaoService service= new UserDaoService(); do same
	
	public UserPostResourceRepoController( UserRepo repository, PostRepo postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri(); 
		return ResponseEntity.created(location).build();
	}

	

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
//		
		return user.get().getPosts();

	}
}
