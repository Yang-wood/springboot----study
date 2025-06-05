package com.example.spring.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring.entitiy.Movie;
import com.example.spring.entitiy.Review;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ReviewRepositoryTest {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
//	@Test
//	void testInsertMovieReviews() {
//		IntStream.rangeClosed(1, 200).forEach(i -> {
//			Long mno = (long)(Math.random() * 100) + 1;
//			
//			Long mid = (long)(Math.random() * 100) + 1;
//			
//			Member member = Member.builder().mId(mid).build();
//			
//			Review movieReview = Review.builder().member(member).movie(Movie.builder().mno(mno).build())
//										.grade((int)(Math.random() * 5 ) + 1)
//										.text("이 영화에 대한 느낌......." + i).build();
//			
//			reviewRepository.save(movieReview);
//		});
//	}
	
//	@Test
//	public void testGetMovieReviews() {
//		Movie movie = Movie.builder().mno(97L).build();
//		
//		List<Review> rs = reviewRepository.findByMovie(movie);
//		
//		rs.forEach(movieReview -> {
//			log.info(movieReview.getReviewnum());
//			log.info(movieReview.getGrade());
//			log.info(movieReview.getText());
//			log.info(movieReview.getMember().getEmail());
//			log.info("==========================================");
//		});
//	}
	
}





