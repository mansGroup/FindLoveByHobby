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
public class ProfileUpdateDto {

	private String userId;
	private int userAge;
	private int userDrinks;
	private int userSmoker;
	private String userHeight;
	private String userIntroduce;
	private int userAcademic;
	private int userIncome;
	private int userJob;
	private int userReligion;
}
