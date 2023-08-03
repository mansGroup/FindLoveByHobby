package com.fin.love.repository.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
@SequenceGenerator(name = "PROFILE_SEQ_GEN", sequenceName = "PROFILE_SEQ", allocationSize = 1)
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;	
	
	@Column
	private int userAge;
	
	@Column(nullable = false)
	private String userPersonality;
	
	@Column(nullable = false)
	private String userDrinks;
	
	@Column(nullable = false)
	private String userSmoker;
	
	@Column
	private String userHeight;
	
	@Column
	private int userAcademic;
	
	@Column
	private int userIncome;
	
	@Column
	private int userJob;
	
	@Column
	private int userReligion;
	
	@Column
	private String userIntroduce;
	
}
