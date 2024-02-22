package com.mytv.api.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")

public class AdminController {
	
	/*
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public List<User> show(){
		
		return userService.show();
	}
	
	
	@GetMapping("/{id}")
	public Optional<User> showbyId(@PathVariable Long id){
		
		return userService.showById(id);
	}
	
	@PutMapping("/update/{id}")
	public User update(@PathVariable Long id, @RequestBody User u){
		
		return userService.upadte(id, u);
		
	}
	
	
	@PostMapping(path="/create")
	public User create(@RequestBody User u) {
		
		return userService.create(u);
	}
	
	
	
	@DeleteMapping(path="/delete/{id}")
	public Boolean delete (@PathVariable Long id) {
		
		userService.delete(id);
		
		return true;
	}

	*/
}
