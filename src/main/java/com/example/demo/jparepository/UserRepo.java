package com.example.demo.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User.User;

											// User is entity & Integer is Id
public interface UserRepo extends JpaRepository<User, Integer> {

}
