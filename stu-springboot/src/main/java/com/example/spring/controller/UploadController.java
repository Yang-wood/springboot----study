package com.example.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UploadController {
	
	@PostMapping("/uploadAjax")
	public void uploadFile(@RequestPart("uploadFiles") MultipartFile[] uploadFiles) {
		
		for (MultipartFile uploadFile : uploadFiles) {
			
			String originName = uploadFile.getOriginalFilename();
			String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
			
			log.info("fileName : {}", fileName);
		}
	}
}
