package com.fin.love.dto.facechat;

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
	
}
