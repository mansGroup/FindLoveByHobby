package com.fin.love.dto.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fin.love.respository.member.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerUserListDto {
	
	private String userId;
	private String name;
	private String nickname;
	private String email;
	private String sex;
	private Role role;
	private String phone;
	private String address;
	private LocalDate birthday;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	private String age;
	private String drinks;
	private String smoker;
	private String height;
	private String academic;
	private String income;
	private String userJob;
	private String religion;
	private String introduce;
	private int sexyCount;
	private int beautifulCount;
	private int cuteCount;
	private int handsomeCount;
	private int wonderfulCount;
	private String hobby1;
	private String hobby2;
	private String hobby3;
	private String pic1;
	private String pic2;
	private String pic3;
	private String hobbyPic1;
	private String hobbyPic2;
	private String hobbyPic3;
	
	
}
