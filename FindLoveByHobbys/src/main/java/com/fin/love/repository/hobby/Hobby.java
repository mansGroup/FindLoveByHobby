package com.fin.love.repository.hobby;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HOBBY")
@SequenceGenerator(name = "HOBBY_SEQ_GEN", sequenceName = "HOBBY_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Hobby {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOBBY_SEQ_GEN")
	private Long hobbyId; // pk 시퀀스
	
	@Column(nullable = false)
	private String content; // ??
	
	@Column(nullable = false)
	private String hobbyName; // 취미 카테고리
	
}
