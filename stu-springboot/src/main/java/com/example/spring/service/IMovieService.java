package com.example.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.spring.dto.MovieDTO;
import com.example.spring.dto.MovieImageDTO;
import com.example.spring.entitiy.Movie;
import com.example.spring.entitiy.MovieImage;

public interface IMovieService {
	
	Long register(MovieDTO movieDTO);
	
	
	// 공부해야봐야 할 내용
	default Map<String, Object> dtoToEntitiy(MovieDTO movieDTO) {
		Map<String, Object> entityMap = new HashMap<>();
		
		// 화면에서 넘어오는 수치를 담음 - 시간은 시스템상에서 처리
		Movie movie = Movie.builder().mno(movieDTO.getMno())
									 .title(movieDTO.getTitle())
									 .build();
		
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDtoList = movieDTO.getImageDTOList();
		
		if (imageDtoList != null && imageDtoList.size() > 0 ) {
			List<MovieImage> movieImageList = imageDtoList.stream()
									.map(movieImageDTO -> {
											MovieImage movieImage = MovieImage.builder()
													.path(movieImageDTO.getPath()).imgName(movieImageDTO.getImgName())
													.uuid(movieImageDTO.getUuid()).movie(movie).build();
													
											return movieImage;										
										}).collect(Collectors.toList());
			entityMap.put("imgList", movieImageList);
		}
		
		return entityMap;
	}
}
