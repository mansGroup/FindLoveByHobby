package com.fin.love.dto.profile;


import com.fin.love.repository.profile.UserHobby;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserHobbyDto {

	private String userId;
	private Long hobbyId;
	
	public UserHobby toEntity() {
		return UserHobby.builder().userid(userId).hobbyId(hobbyId).build();
	}
}
