package com.example.demo.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User.Post;

											// Post is entity & Integer is Id
public interface PostRepo extends JpaRepository<Post, Integer> {

}
