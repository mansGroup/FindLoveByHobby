package com.fin.love.repository.facechat;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "SPEAKCHAT")
@SequenceGenerator(name = "SPEAKCHAT_SEQ_GEN", sequenceName = "SPEAKCHAT_SEQ", allocationSize = 1)
public class Speakchat {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator =  "SPEAKCHAT_SEQ_GEN")
	private long id;
	
	@Column(nullable = true)
	private String chatfile;
	
	@Column(nullable = false)
	private LocalDateTime createdTime = LocalDateTime.now();
	
	@Column(nullable = false)
	private int report;
	
	@Column(nullable = false)
	private String reporter;
	
	@Column(nullable = false)
	private String respondent;
	
	
	@ManyToOne
	@JoinColumn(name="roomid")
	private Speakroom speakroom;
	
	// 녹음 파일 경로 들어오면 저장.
	public void saveMyReportData(String root) {
		
		this.chatfile = root;
		
	}
	
	
}
