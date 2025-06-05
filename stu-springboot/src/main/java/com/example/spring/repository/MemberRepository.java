package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
