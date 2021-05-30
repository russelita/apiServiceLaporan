package com.api.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.main.entity.User;
import com.api.main.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserPage {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	@PostMapping("/register")
	public String addUser(@RequestBody User user){
		userRepo.save(user);
		return "insert berhasil";
	}
	
	@GetMapping("/login")
	public User loginUser(@RequestParam("name")String name, @RequestParam("password") String password) {
		return userRepo.findByLogin(name, password);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id) {
		userRepo.deleteById((id));
		return "Delete Berhasil";
	}
	
	@PutMapping("/update/{id}")
	public String updateById(@PathVariable Long id, @RequestBody User user){
		user.setId(id);
		userRepo.save(user);
		return "Update Berhasil";
	}

}
