package com.example.demo.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	

	
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 10)
	private String description;


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	// here many to one becoz many posts has 1 user and fetch type(lazy) will fetch data lazily from DB and when we are fetching user data using post entity() 
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user; 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
