package com.api.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.main.entity.Laporan;
import com.api.main.repository.LaporanRepository;
import com.api.main.utility.FileUtility;

@RestController
@RequestMapping("/laporan")
public class LaporanPage {
	
	@Autowired
	LaporanRepository laporRepo;
	
	@GetMapping("/")
	public List<Laporan>getAll(){
		return laporRepo.findAll();
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody Laporan laporan,Laporan datetime,@RequestParam(value = "file")MultipartFile file) throws IOException { {
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
	        
	        String uploadDir = "user-photos/" ;

	        FileUtility.saveFile(uploadDir, fileName, file);
	 
	      laporan.setGambar("/"+uploadDir + fileName);
	      laporan.setStatus("pending");
		this.laporRepo.save(laporan);
		this.laporRepo.save(datetime);
		return "insert berhasil";
	}
	}
	
//	@PostMapping("/ujian/dashboard/view")
//	  public String addLaporan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Laporan laporan, Model model) throws IOException { {
//		
//		// buat penampung data mahasiswa di halaman htmlnya
//		  String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//			
//	        
//	        String uploadDir = "user-photos/" ;
//
//	        FileUtility.saveFile(uploadDir, fileName, file);
//	 
//	      laporan.setGambar("/"+uploadDir + fileName);
//	      laporan.setStatus("pending");
//		this.modelLaporan.addLaporan(laporan);
//		model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
//		
//		
//		return "redirect:/ujian/dashboard/view";
//	}
//	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id) {
		laporRepo.deleteById((id));
		return "Delete Berhasil";
	}
	
	@PutMapping("/update/{id}")
	public String updateById(@PathVariable Long id, @RequestBody Laporan laporan){
		laporan.setId(id);
		laporRepo.save(laporan);
		return "Update Berhasil";
	}
	

}
