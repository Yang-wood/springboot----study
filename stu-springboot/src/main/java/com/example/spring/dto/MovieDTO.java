package com.example.spring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
	private Long mno;
	private String title;
	
	@Builder.Default
	private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
