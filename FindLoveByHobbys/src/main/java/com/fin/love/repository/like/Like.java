package com.fin.love.repository.like;

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
@Table(name = "LIKES")
@SequenceGenerator(name = "LIKES_SEQ_GEN", sequenceName = "LIKES_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKES_SEQ_GEN")
	private Long id; // pk 시퀀스
	
	@Column(nullable = false)
	private String sender; // 좋아요를 보내는 사람 아이디.
	
	@Column(nullable = false)
	private String recipient; // 좋아요를 받는 사람 아이디.
	
	@Column(columnDefinition = "CHECK(value BETWEEN 0 AND 1)") // 필드 값이 0과 1 사이인지를 체크하는 제약조건을 설정
	private int whether; // 수락여부, (0 = 수락 안함, 1 = 수락 확인)

	public void chageWhether(int i) {
		this.whether = i;
	}
}
