package com.fin.love.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService inMemoryUserDeatailsService() {
		// 사용자 상세 정보
		UserDetails user1 = User.withUsername("user1") // 로그인할 때 사용할 사용자 이름
				.password(passwordEncoder().encode("1111")) // 로그인 시 사용할 암호
				.roles("USER") // 사용자 권한(USER, ADMIN, ...
				.build(); // UserDetails 객체 생성.
		
		return new InMemoryUserDetailsManager(user1);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf) -> csrf.disable());
		
		http.logout((logout) -> logout.logoutSuccessUrl("/"));
		
		return http.build();
	}
}
