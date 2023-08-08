package com.fin.love.repository.profile;


import com.fin.love.dto.profile.ProfileUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "PROFILE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Profile {
	
	@Id
	private String userId;	
	
	@Column
	private int userAge;
	
	@Column(nullable = false)
	private int userDrinks;
	
	@Column(nullable = false)
	private int userSmoker;
	
	@Column
	private int userHeight;
	
	@Column
	private String userIntroduce;
	
	@Column
	private int userAcademic;
	
	@Column
	private int userIncome;
	
	@Column
	private int userJob;
	
	@Column
	private int userReligion;
	
	public Profile saveData(
			String userId, int userAge, int userDrinks, int userSmoker, int userHeight, String userIntroduce,
			int userAcademic, int userIncome, int userJob, int userReligion
			) {
		this.userId = userId;
		this.userAge = userAge;
		this.userDrinks = userDrinks;
		this.userSmoker = userSmoker;
		this.userHeight = userHeight;
		this.userIntroduce = userIntroduce;
		this.userAcademic = userAcademic;
		this.userIncome = userIncome;
		this.userJob = userJob;
		this.userReligion = userReligion;
		
		return this;
	}
	
	public Profile update(ProfileUpdateDto dto) {
		this.userId = dto.getUserId();
		this.userAge = dto.getUserAge();
		this.userDrinks = dto.getUserDrinks();
		this.userSmoker = dto.getUserSmoker();
		this.userHeight = dto.getUserHeight();
		this.userIntroduce = dto.getUserIntroduce();
		this.userAcademic = dto.getUserAcademic();
		this.userIncome = dto.getUserIncome();
		this.userJob = dto.getUserJob();
		this.userReligion = dto.getUserReligion();
		
		return this;
	}
	
}
