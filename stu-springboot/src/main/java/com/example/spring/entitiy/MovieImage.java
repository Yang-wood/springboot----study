package com.example.spring.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@SequenceGenerator(
			name = "MOVIEIMAGE_SEQ_GEN",
			sequenceName = "movieimage_seq",
			initialValue = 1,
			allocationSize = 1
		)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MOVIEIMAGE_SEQ_GEN")
	private Long inum;
	
	private String uuid;
	
	private String imgName;
	
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)  // LAZY : 지연 , EAGER : 즉시
	private Movie movie;
}
