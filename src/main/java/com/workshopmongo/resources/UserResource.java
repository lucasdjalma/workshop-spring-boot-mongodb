package com.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshopmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@GetMapping 
	public ResponseEntity< List<User> >findAll() {
	User Maria = new User("1", "Maria Brown", "maria@gmail.com");
	User Alex = new User("2", "Alex Green", "alexgmail.com");
	List<User> list = new ArrayList<>();
	list.addAll(Arrays.asList(Maria, Alex));
	return ResponseEntity.ok().body(list); 
	} 
	
}
