package com.example.spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entitiy.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
//	@Test
//	void testInsertMembers() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			Member member = Member.builder().email("user" + i + "@naver.com").pw("1111")
//											.nickname("reviewer" + i).build();
//			memberRepository.save(member);
//		});
//	}

	@Transactional
	@Commit
	@Test
	public void testDeleteMembers() {
		Long mid = 6L;
		
		Member member = Member.builder().mId(mid).build();
		
		memberRepository.deleteById(mid);
		reviewRepository.deleteByMember(member);
		
		log.info(mid);
		log.info(member);
	}

}














