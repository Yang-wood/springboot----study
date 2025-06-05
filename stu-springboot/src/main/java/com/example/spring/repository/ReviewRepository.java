package com.example.spring.repository;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.entitiy.Member;
import com.example.spring.entitiy.Movie;
import com.example.spring.entitiy.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	// 동시에 로딩해서 오는 방법
	@EntityGraph(attributePaths = {"member"}, type = EntityGraphType.FETCH)
	List<Review> findByMovie(Movie movie);
	
	// 성능 up, 리뷰 쪽 삭제 시 전체삭제
	@Modifying
	@Query("DELETE FROM Review r "
		 + "WHERE r.member = :memeber ")
	public void deleteByMember(@Param("memeber") Member member);
	
	
}
