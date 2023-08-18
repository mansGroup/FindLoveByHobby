package com.fin.love.respository.member;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import com.fin.love.dto.member.UpdateInfoDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fin.love.repository.BaseTimeEntity;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "USERINFO")
public class Member extends BaseTimeEntity implements UserDetails {

	@Id
	private String id;
	
	@Column(nullable = false) 
    private String name;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private int sex;
    
    @Column(nullable = true)
    private Role role;
	
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String nickname;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private LocalDate birthday;
    
    @Builder
    private Member(String id, String name, String password, String email, int sex, Role role,
    			String phone, String nickname, String address, LocalDate birthday) {
    	this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.role = role;
        this.phone = phone;
        this.nickname = nickname;
        this.address = address;
        this.birthday = birthday;
        		
    }
	
    public String getEmail() {
    	return this.email;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_USER 권한을 갖음.
        return Arrays.asList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    // 계정이 만료되었는지 확인하는 메서드
    public boolean isAccountNonExpired() {
        return true; // 계정(account)이 non-expired(만료되지 않음)
    }

    @Override
    // 잠겨있는 계정인지?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호가 non-expired 인지?
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용자 상세정보(UserDetails)가 활성화(enable).
    }

	@Override
	public String getUsername() {
		return this.id;
	}
	
	public Member updateRole(Role role) {
		this.role = role;
		
		return this;
	}

    public void updatePassword(String temporaryPassword) {
        this.password = temporaryPassword;
    }

    public Member updateInfo(UpdateInfoDto dto) {
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();

        return this;
    }
}
