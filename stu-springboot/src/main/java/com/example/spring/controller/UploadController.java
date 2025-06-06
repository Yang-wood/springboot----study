package com.example.spring.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring.dto.UploadResultDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadController {

	@Value("${com.spring.upload.path}")
	private String uploadPath;
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(@RequestPart("uploadFiles") MultipartFile[] uploadFiles) {
		List<UploadResultDTO> resultDtoList = new ArrayList<>();
		
		for (MultipartFile uploadFile : uploadFiles) {
			
			if (uploadFile.getContentType().startsWith("image") == false) {
				log.warn("이미지 파일이 아닙니다.");
				
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			
			log.info("fileName : {}", fileName);
			
			String folderPath = makeFolder();
			
			String uuid = UUID.randomUUID().toString();
			
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
			
			Path savePath = Paths.get(saveName);
			
			try {
				uploadFile.transferTo(savePath);
				
				String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
				
				File thumbnailFile = new File(thumbnailSaveName);
				
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
				
				resultDtoList.add(new UploadResultDTO(fileName, uuid, folderPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
	}

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		String folderPath = str.replace("//", File.separator);
		
		File uploadPathFolder = new File(uploadPath, folderPath);
		
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		
		return folderPath;
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName) {
		ResponseEntity<byte[]> result = null;
		
		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info("srcFileName : {}", srcFileName);
			
			File file = new File(uploadPath + File.separator + srcFileName);
			log.info("file : {}", file);
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(@RequestParam("fileName") String fileName) {
		String srcFileName = null;
		
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator + srcFileName);
			
			boolean result = file.delete();
			
			File thumbnail = new File(file.getParent(), "s_" + file.getName());
			result = thumbnail.delete();
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










