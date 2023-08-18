package com.fin.love.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService inMemoryUserDeatailsService() {
//		// 사용자 상세 정보
//		UserDetails user1 = User.withUsername("user1") // 로그인할 때 사용할 사용자 이름
//				.password(passwordEncoder().encode("1111")) // 로그인 시 사용할 암호
//				.roles("USER") // 사용자 권한(USER, ADMIN, ...
//				.build(); // UserDetails 객체 생성.
//		
//		return new InMemoryUserDetailsManager(user1);
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf) -> csrf.disable());
		
		http.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					// 로그인 하지 않아도 접근 가능한 주소 설정

					.requestMatchers("/", "/login", "/css/**", "/images/**", "/js/**", "/fonts/**", "/member/login", "/member/signup","/api/member/**","/api/**", "/member/findid", "/member/findpassword").permitAll()
					.anyRequest().authenticated()

				);
		
		http.formLogin(login -> login
				.loginPage("/member/login")	// 커스텀 로그인 페이지 지정
				.failureUrl("/member/login?error=true")
//				.loginProcessingUrl("/member/login2") // submit받을 url
				.usernameParameter("username") // submit할 아이디
				.passwordParameter("password") // submit할 패스워드
				.defaultSuccessUrl("/member/loginsuccess", true) // 로그인 성공 시 이동할 페이지
				);
		
		http.logout((logout) -> logout.logoutSuccessUrl("/"));
		
		return http.build();
	}
}
