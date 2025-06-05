package com.example.spring.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MovieRepositoryTests {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository movieImageRepository;
	
//	@Commit
//	@Transactional
//	@Test
//	void testInsertMovies() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			Movie movie = Movie.builder().title("Movie...." + i).build();
//			
//			log.info("----------------------------------------------");
//			movieRepository.save(movie);
//			
//			int count = (int)(Math.random() * 5 ) + 1;
//			
//			for (int j = 0; j < count; j++) {
//				MovieImage movieImage = MovieImage.builder().uuid(UUID.randomUUID().toString())
//															.movie(movie).imgName("test" + j + ".jpg").build();
//			movieImageRepository.save(movieImage);
//			}
//			log.info("===============================================");
//		});
//	}
	
//	@Test
//	public void testListPage() {
//		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
//		
//		Page<Object[]> rs = movieRepository.getListPage(pageRequest);
//		
//		for (Object[] objects : rs.getContent()) {
//			log.info(Arrays.toString(objects));
//		}
//	}
	
	@Test
	public void testGetMovieWithAll() {
		List<Object[]> rs = movieRepository.getMovieWithAll(100L);
		
		log.info(rs);
		
		for (Object[] objects : rs) {
			log.info(Arrays.toString(objects));
		}
	}
	
}
