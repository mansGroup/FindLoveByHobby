package com.fin.love.dto.matching;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchingListDto {
	
	private String userId;
	private String nickname;
	private String userAge;
	private String userIntrodus;
	private String assessment;
	private int assessmentCount;
	
}
