package com.fin.love.dto.matching;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchingListDto {
	
	private String nickname;
	private int userAge;
	private String assessment;
	private int assessmentCount;
	
	
	
}
