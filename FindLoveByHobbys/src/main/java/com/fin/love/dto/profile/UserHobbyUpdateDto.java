package com.fin.love.dto.profile;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserHobbyUpdateDto {
	
	private Long id;
	private String userid;
	private Long hobbyId;

}
