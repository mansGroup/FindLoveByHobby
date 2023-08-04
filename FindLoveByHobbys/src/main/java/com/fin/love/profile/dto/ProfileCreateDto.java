package com.fin.love.profile.dto;


import com.fin.love.repository.profile.Profile;

import lombok.Data;

@Data
public class ProfileCreateDto {

	private String userId;
	private String userAge;
	private String userDrinks;
	private String userSmoker;
	private String userHeight;
	private String userIntroduce;
	private String userAcademic;
	private String userIncome;
	private String userJob;
	private int userReligion;
	
	public Profile toEntity() {
		return Profile.builder()
				.userId(userId)
				.userAge(userAge)
				.userPersonality("TEST 테스트 값")
				.userDrinks(userDrinks)
				.userSmoker(userSmoker)
				.userHeight(userHeight)
				.userIntroduce(userIntroduce)
				.userAcademic(userAcademic)
				.userIncome(userIncome)
				.userJob(userJob)
				.userReligion(userReligion)
				.build();
	}
}
