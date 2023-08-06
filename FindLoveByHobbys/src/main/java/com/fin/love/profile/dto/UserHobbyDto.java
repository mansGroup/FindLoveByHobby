package com.fin.love.profile.dto;


import com.fin.love.repository.profile.UserHobby;

import lombok.Data;

@Data
public class UserHobbyDto {

	private Long id;
	private String userId;
	private int hobbyId;
	
	public UserHobby toEntity() {
		return UserHobby.builder().id(id).userId(userId).hobbyId(hobbyId).build();
	}
}
