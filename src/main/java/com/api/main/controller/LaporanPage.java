package com.api.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.main.entity.Laporan;
import com.api.main.repository.LaporanRepository;
import com.api.main.utility.FileUtility;
import com.google.gson.Gson;

@RestController
@RequestMapping("/laporan")
public class LaporanPage {
	
	@Autowired
	LaporanRepository laporRepo;
	
	@GetMapping("/")
	public List<Laporan>getAll(){
		return laporRepo.findAll();
	}
	
	@PostMapping("/")
	public String addLaporan (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir = "src/main/java/user-photo/";
		FileUtility.saveFile(uploadDir, fileName, images);
		Laporan lapor = new Gson().fromJson(dataJson, Laporan.class);
		
		if(lapor.getKejadian().equalsIgnoreCase("bencana")) {
			lapor.setStatus("bencana");
		}else {
			lapor.setStatus("kriminal");
		}
		lapor.setImage(fileName);
		Date date = new Date();
		lapor.setJam(String.valueOf(date.getHours())+":"+String.valueOf(date.getMinutes()));
		laporRepo.save(lapor);
		return "Berhasil memasukan data";
	}
	
	@GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
	   final InputStream in = getClass().getResourceAsStream("/user-photo/"+name);
	   return IOUtils.toByteArray(in);
	}
	
//	@PostMapping("/add")
//	public String addLaporan (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
//		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
//		
//		String uploadDir = "user-photo/";
//		FileUtility.saveFile(uploadDir, fileName, images);
//		GsonJsonParser data = new GsonJsonParser();
//		Laporan lapor = new Gson().fromJson(dataJson, Laporan.class);
//		lapor.setImage("/" + uploadDir + fileName);
//		laporRepo.save(lapor);
//		return "Berhasil memasukan data";
//		}
	
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
