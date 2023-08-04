package com.fin.love.profile.dto;

import java.sql.Timestamp;

import com.fin.love.respository.member.Member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileReadUserInfoDto {

	private String name;
	private int sex;
	private Timestamp birthday;
	
	public static ProfileReadUserInfoDto fromEntity(Member entity) {
		return ProfileReadUserInfoDto.builder()
				.name(entity.getName())
				.sex(entity.getSex())
				.birthday(entity.getBirthday())
				.build();
	}
	
}
