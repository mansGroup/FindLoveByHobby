package com.fin.love.dto.profile;


import com.fin.love.repository.profile.Profile;

import lombok.Data;

@Data
public class ProfileCreateDto {

	private String userId;
	private int userAge;
	private int userDrinks;
	private int userSmoker;
	private int userHeight;
	private String userIntroduce;
	private int userAcademic;
	private int userIncome;
	private int userJob;
	private int userReligion;
	
	public Profile toEntity() {
		return Profile.builder()
				.userId(userId)
				.userAge(userAge)
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
