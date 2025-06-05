package com.example.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.dto.MovieDTO;
import com.example.spring.entitiy.Movie;
import com.example.spring.entitiy.MovieImage;
import com.example.spring.repository.MovieImageRepository;
import com.example.spring.repository.MovieRepository;
import com.example.spring.service.IMovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor  // Autowired 안 쓸 수 있는 방식임
public class MovieServiceImpl implements IMovieService{
	
	private final MovieRepository movieRepository;
	private final MovieImageRepository imageRepository;
	
	@Transactional
	@Override
	public Long register(MovieDTO movieDTO) {
		
		Map<String, Object> entityMap = dtoToEntitiy(movieDTO);
		Movie movie = (Movie)entityMap.get("movie");
		
		List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");
		
		movieRepository.save(movie);
		movieImageList.forEach(movieImage -> {
			imageRepository.save(movieImage);
		});
		
		return movie.getMno();
	}

}
