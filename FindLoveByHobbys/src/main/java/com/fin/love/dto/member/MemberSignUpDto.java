package com.fin.love.dto.member;



import java.time.LocalDate;

import com.fin.love.respository.member.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberSignUpDto {
	
	@Size(min = 5, max = 10, message = "ID는 5자 이상 10자 이하로 입력해주세요.")
	@NotBlank(message = "ID를 입력해주세요.")
	private String userid;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
	
	@NotBlank(message = "이름을 입력하세요.")
    private String username;
    
	private Role role;
    private LocalDate birthdate;
    private int sex;
    
    private String address;
    private String useraddress;
    private String userdetailaddress;
    private String useraddressnotes;
    
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxxx-xxxx")
    @NotEmpty(message = "핸드폰 번호를 입력해주세요")
    private String phone;
    
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
    
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;
	
}
