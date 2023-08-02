package com.fin.love.dto.member;



import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberSignUpDto {

	private String userid;
    private String password;
    private String username;
    
    private LocalDate birthdate;
    private int sex;
    private String address;
    private String phone;
    private String email;
    private String nickname;
	
}
