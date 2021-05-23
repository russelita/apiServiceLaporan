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

import com.api.main.entity.Buku;
import com.api.main.repository.BukuRepository;

@RestController
@RequestMapping("/buku")
public class BukuPage {
	@Autowired
	BukuRepository bukuRepo;
	
	@GetMapping("/get")
	public List<Buku> getAll(){
		return (List<Buku>) bukuRepo.findAll();
	}
	
	@PostMapping("/add")
	public String addBuku(@RequestBody Buku buku){
		this.bukuRepo.save(buku);
		return "insert berhasil";
	}

	@GetMapping("/search/{title}")
	public List<Buku> getAllByJudulBuku(@PathVariable("title") String title){
		return bukuRepo.getDataByJudulBuku(title);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBukuByJudulBuku(@PathVariable Long id) {
		bukuRepo.deleteById((id));
		return "Delete Berhasil";
	}
	
	@PutMapping("/update/{id}")
	public String updateByJudulBuku(@PathVariable Long id, @RequestBody Buku buku){
		buku.setId(id);
		bukuRepo.save(buku);
		return "Update Berhasil";
	}
	
	
}
