package com.fin.love.dto.facechat;

import java.time.LocalDateTime;

import com.fin.love.repository.facechat.SpeakChat;
import com.fin.love.repository.facechat.SpeakRoom;

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
	
	public SpeakChat toEntity(SpeakRoom room) {
		
		return SpeakChat.builder().chatfile(filepath).createdTime(LocalDateTime.now()).speakRoom(room).report(report).build();
		
	}
	
}
