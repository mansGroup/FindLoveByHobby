package com.fin.love.dto.member;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberSignUpDto {

	private String userid;
    private String password;
    private String username;
    private Timestamp birthdate;
    private int sex;
    private String phone;
    private String email;
    private String nickname;
    private String address;
	
    
}
