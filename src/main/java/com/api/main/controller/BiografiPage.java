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
import org.springframework.web.bind.annotation.RestController;

import com.api.main.entity.Biografi;
import com.api.main.repository.BiografiRepository;

@RestController
@RequestMapping("/bio")
public class BiografiPage {
	
	@Autowired
	BiografiRepository bioRepo;
	
	@GetMapping("/get")
	public List<Biografi> getAll(){
		return (List<Biografi>) bioRepo.findAll();
	}
	
	@PostMapping("/add")
	public String addBio(@RequestBody Biografi bio){
		this.bioRepo.save(bio);
		return "insert berhasil";
	}

	@GetMapping("/search/{type}/{value}")
	public List<Biografi> getSearchBy(@PathVariable("type") String type, @PathVariable("value") String value ){
		return bioRepo.findBySearchBy(type, value);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBioById(@PathVariable Long id) {
		bioRepo.deleteById((id));
		return "Delete Berhasil";
	}
	
	@PostMapping("/update/{id}")
	public String updateBioById(@PathVariable Long id, @RequestBody Biografi bio){
		bio.setId(id);
		bioRepo.save(bio);
		return "Update Berhasil";
	}

}
