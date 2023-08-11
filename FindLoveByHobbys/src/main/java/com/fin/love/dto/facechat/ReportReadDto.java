package com.fin.love.dto.facechat;

import java.time.LocalDateTime;

import org.springframework.core.io.Resource;

import com.fin.love.repository.facechat.Speakchat;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportReadDto {

	private long id;

	private String chatfile;

	private LocalDateTime createdTime = LocalDateTime.now();

	private int report;

	private String reporter;

	private String respondent;
	
	private String audio;
	
	public void resourceaddon(String audio) {
		
		this.audio = audio;
		
	}
	
	public static ReportReadDto fromEntity(Speakchat chat) {
		
		return ReportReadDto.builder().chatfile(chat.getChatfile()).createdTime(chat.getCreatedTime()).id(chat.getId()).report(chat.getReport()).reporter(chat.getReporter()).respondent(chat.getRespondent()).build(); 
		
	}
	
}
