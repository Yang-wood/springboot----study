package com.example.spring.dto;

import java.net.URLEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {

	private String fileName;
	private String uuid;
	private String folderPath;
	
	@JsonProperty("imageURL")
	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@JsonProperty("thumbnailURL")
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
