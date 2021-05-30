package com.api.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.main.entity.DataUserJodoh;
import com.api.main.repository.DataUserJodohRepository;
import com.google.gson.Gson;
import com.api.main.utility.FileUtility;

@RestController
@RequestMapping("/userjodoh")
public class DataUserJodohPage {
	
	@Autowired
	DataUserJodohRepository dataRepo;
	
	@GetMapping("/")
	public List<DataUserJodoh> getAllUsers(){
		return dataRepo.findAll();
	}
	
	@GetMapping("/login")
	public DataUserJodoh loginUser(@RequestParam("name")String username, @RequestParam("password") String phone) {
		return dataRepo.findByLogin(username, phone);
	}
	
	@PostMapping("/")
	public String addData (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir = "src/main/java/user-photo/";
		FileUtility.saveFile(uploadDir, fileName, images);
		DataUserJodoh data = new Gson().fromJson(dataJson, DataUserJodoh.class);
		
		if(data.getJenisKelamin().equalsIgnoreCase("Laki-Laki")) {
			data.setJenisKelamin("Laki-Laki");
//			dataRepo.findByJenisKelamin("Perempuan");
		}else {
			data.setJenisKelamin("Perempuan");
//			dataRepo.findByJenisKelamin("Laki-Laki");
		}
		data.setImage(fileName);
		dataRepo.save(data);
		return "Berhasil memasukan data";
	}
	
	@GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
	   final InputStream in = getClass().getResourceAsStream("/user-photo/"+name);
	   return IOUtils.toByteArray(in);
	}

}
