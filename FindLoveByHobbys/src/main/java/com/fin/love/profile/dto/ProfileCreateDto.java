package com.fin.love.profile.dto;


import com.fin.love.repository.profile.Profile;

import lombok.Data;

@Data
public class ProfileCreateDto {

	private String user_Id;
	private int user_Age;
	private String uset_Personality;
	private String user_Drinks;
	private String user_Smoker;
	private String user_Height;
	private String user_Introduce;
	private int user_Academic;
	private int user_Income;
	private int user_Job;
	private int user_Religion;
	
	public Profile toEntity() {
		return Profile.builder()
				.user_Id(user_Id)
				.user_Age(user_Age)
				.user_Personality("TEST 테스트 값")
				.user_Drinks(user_Drinks)
				.user_Smoker(user_Smoker)
				.user_Height(user_Height)
				.user_Introduce(user_Introduce)
				.user_Academic(user_Academic)
				.user_Income(user_Income)
				.user_Job(user_Job)
				.user_Religion(user_Religion)
				.build();
	}
}
