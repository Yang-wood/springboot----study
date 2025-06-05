package com.example.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.entitiy.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("SELECT m, mi, "
		 + "	  avg(coalesce(r.grade, 0)), "
		 + "	  count(distinct r) "
		 + "FROM Movie m LEFT JOIN MovieImage mi ON mi.movie = m "
		 + "AND mi.inum = (SELECT MAX(mi2.inum) "
		 + "				FROM MovieImage mi2 "
		 + "				WHERE mi2.movie = m) "
		 + "LEFT JOIN Review r ON r.movie = m "
		 + "GROUP BY m, mi ")
	Page<Object[]> getListPage(Pageable pageable);
	
	@Query("SELECT m, mi, AVG(coalesce(r.grade, 0)), count(r) "
	  	 + "FROM Movie m LEFT OUTER JOIN MovieImage mi ON mi.movie = m "
	  	 + "LEFT OUTER JOIN Review r on r.movie = m "
	  	 + "WHERE m.mno = :mno "
	  	 + "GROUP BY m, mi ")
	List<Object[]> getMovieWithAll(@Param("mno") Long mno);
	
}







