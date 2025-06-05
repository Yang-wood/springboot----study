package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entitiy.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

}
