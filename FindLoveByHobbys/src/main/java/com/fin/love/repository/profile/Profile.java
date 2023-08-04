package com.fin.love.repository.profile;


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
	private String userAge;
	
	@Column(nullable = false)
	private String userPersonality;
	
	@Column(nullable = false)
	private String userDrinks;
	
	@Column(nullable = false)
	private String userSmoker;
	
	@Column
	private String userHeight;
	
	@Column
	private String userIntroduce;
	
	@Column
	private String userAcademic;
	
	@Column
	private String userIncome;
	
	@Column
	private String userJob;
	
	@Column
	private int userReligion;
	
	public Profile updateData(
			String userAge, String userDrinks, String userHeight, String userIntroduce,
			String userAcademic, String userIncome, String userJob, int userReligion
			) {
		this.userAge = userAge;
		this.userDrinks = userDrinks;
		this.userHeight = userHeight;
		this.userIntroduce = userIntroduce;
		this.userAcademic = userAcademic;
		this.userIncome = userIncome;
		this.userJob = userJob;
		this.userReligion = userReligion;
		this.userPersonality = "123455678";
		
		return this;
	}
	
}
