package com.mytv.api.controller.gestUser;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytv.api.model.gestUser.User;
import com.mytv.api.service.gestUser.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path="/create")
	public User create(@RequestBody User u) {
		
		return userService.create(u);
	}
	
	@GetMapping("/show")
	public List<User> show(){
		
		return userService.show();
	}
	
	@DeleteMapping(path="/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		userService.delete(id);
		
		return true;
	}


}
