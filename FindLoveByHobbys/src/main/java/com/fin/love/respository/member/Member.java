package com.fin.love.respository.member;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

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
	
	@Column(nullable = false, unique = true) // No Null, UNIQUE 제약 조건
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
    private Timestamp birthday;
    
    @Builder
    private Member(String name, String password, String email, int sex,
    			String phone, String nickname, String address, Timestamp birthday) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.role = Role.USER;
        this.phone = phone;
        this.nickname = nickname;
        this.address = address;
        this.birthday = birthday;
        		
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
		// TODO Auto-generated method stub
		return null;
	}
    
}
