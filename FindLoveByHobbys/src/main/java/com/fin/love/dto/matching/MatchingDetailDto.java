package com.fin.love.dto.matching;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchingDetailDto {
	
	private Long hobbyId;
	private String content;
	private String hobbyName;
	private Long id;
	private String userid;
}
