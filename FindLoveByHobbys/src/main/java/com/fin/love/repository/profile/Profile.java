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
	private String user_Id;	
	
	@Column
	private int user_Age;
	
	@Column(nullable = false)
	private String user_Personality;
	
	@Column(nullable = false)
	private String user_Drinks;
	
	@Column(nullable = false)
	private String user_Smoker;
	
	@Column
	private String user_Height;
	
	@Column
	private String user_Introduce;
	
	@Column
	private int user_Academic;
	
	@Column
	private int user_Income;
	
	@Column
	private int user_Job;
	
	@Column
	private int user_Religion;
	
	public Profile updateData(
			int user_Age, String user_Personality, String user_Drinks, String user_Smoker, String user_Height, String user_Introduce,
			int user_Academic, int user_Income, int user_Job, int user_Religion
			) {
		this.user_Age = user_Age;
		this.user_Personality = "123455678";
		this.user_Drinks = user_Drinks;
		this.user_Smoker = user_Smoker;
		this.user_Height = user_Height;
		this.user_Introduce = user_Introduce;
		this.user_Academic = user_Academic;
		this.user_Income = user_Income;
		this.user_Job = user_Job;
		this.user_Religion = user_Religion;
		
		return this;
	}
	
}
