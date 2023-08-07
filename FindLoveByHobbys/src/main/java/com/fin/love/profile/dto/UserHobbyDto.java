package com.fin.love.profile.dto;


import com.fin.love.repository.profile.UserHobby;

import lombok.Data;

@Data
public class UserHobbyDto {

	private Long id;
	private String userid;
	private Long hobbyId;
	
	public UserHobby toEntity() {
		return UserHobby.builder().id(id).userid(userid).hobbyId(hobbyId).build();
	}
}
