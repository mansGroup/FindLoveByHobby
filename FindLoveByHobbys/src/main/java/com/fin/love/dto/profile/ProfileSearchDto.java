package com.fin.love.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileSearchDto {
	
	private String userId;
	private String name;
	private String age;
	private String height;
	private String introduce;
	private String pic;
	
}
