package com.fin.love.dto.facechat;

import java.time.LocalDateTime;

import com.fin.love.repository.facechat.Speakchat;
import com.fin.love.repository.facechat.Speakroom;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ReportFaceChatDto {
	
	private long roomId; 
	private int report;
	private String filepath;
	
	public Speakchat toEntity(Speakroom room) {
		
		return Speakchat.builder().chatfile(filepath).createdTime(LocalDateTime.now()).speakroom(room).report(report).build();
		
	}
	
}
