package com.example.demo.dao.service.layer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.example.demo.User.User;

@Component
public class UserDaoService {
	// JPA/Hibernate > Database
	// UserDaoService > Static List

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	static {
//		users.add(new User(1,"Adam",LocalDate.now().minusYears(30)));
//		users.add(new User(2,"Eve",LocalDate.now().minusYears(25)));
//		users.add(new User(3,"Jim",LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
	}

	public List<User> findAll() {
		return users;
	}

	// public User save(User user) {

	public User findFirstOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
//		Above equal method is inheritated from object into Integer so using here to compare the passed id on method7
		
		return users.stream().filter(predicate).findFirst().orElse(null);
		
		//return users.stream().filter(predicate).findFirst().get().orElse(null);
		//		Above filter() [is using as if condition to check condition] findFirst() is taken from optional class so using here to find 1st value and return found value using get() else get will send no element found exception 

	}

	public User save(User useradd) {
		useradd.setId(++usersCount);
		users.add(useradd);
		return useradd;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
//		Above equal method is inheritated from object into Integer so using here to compare the passed id on method7
		users.removeIf(predicate);		
	}

}