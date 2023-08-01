package com.fin.love.repository.facechat;

import java.time.LocalDateTime;

import com.fin.love.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "SPEAKROOM")
public class SpeakRoom {

	@Id
	private long roomId;
	
	@Column(nullable = false)
	private String speakmember1;
	
	@Column(nullable = false)
	private String speakmember2;
	
	@Column(nullable = false)
	private LocalDateTime createdTime;
	
}
