package com.example.spring.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@SequenceGenerator(
		name = "MOVIE_SEQ_GEN",			// 
		sequenceName = "movie_seq",		// 시퀀스 이름
		initialValue = 1,				// 초기값
		allocationSize = 1				// 증가값
		)
@Builder // 빌더를 쓰기 위해선 아래 2개 필수!!
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,			// 생성된 시퀀스 지정
					generator = "MOVIE_SEQ_GEN") 
	private Long mno;
	
	private String title;
	
}
