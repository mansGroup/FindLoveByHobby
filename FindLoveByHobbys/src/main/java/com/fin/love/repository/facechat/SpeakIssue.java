package com.fin.love.repository.facechat;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//@Entity
@Table(name = "SPEAKCHAT")
@SequenceGenerator(name = "SPEAKCHAT_SEQ_GEN", sequenceName = "SPEAKCHAT_SEQ", allocationSize = 1)
public class SpeakIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator =  "SPEAKCHAT_SEQ")
	private long id;
	
	@Column(nullable = true)
	private String chatfile;
	
	@Column(nullable = false)
	private LocalDateTime createdTime = LocalDateTime.now();
	
	@Column(nullable = false)
	private int report;
	
	@OneToOne
	private SpeakRoom speakRoom;
	
	// 녹음 파일 경로 들어오면 저장.
	public void saveMyReportData(String root) {
		
		this.chatfile = root;
		
	}
	
	
}
