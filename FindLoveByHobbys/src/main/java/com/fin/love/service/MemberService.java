package com.fin.love.service;

import com.fin.love.repository.chat.ChattingRoom;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final PasswordEncoder passwordEncoder;
	
	private final MemberRepository memberRepository;
	

	public String signUp(MemberSignUpDto dto) {
		log.info("signUp={dto=()}", dto);
		
		Member entity = Member.builder()
				.id(dto.getUserid())
				.name(dto.getUsername())
				.password(passwordEncoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.sex(dto.getSex())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.birthday(dto.getBirthdate())
				.build();
		 log.info("save 전: entity={}", entity);
	      
	     memberRepository.save(entity);
	     log.info("save 후: entity={}", entity);
	     
	     return entity.getId();
	     
	}
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		log.info("loadUserByUsername(id={})", id);
		
		Member user = memberRepository.findById(id).orElseThrow();
		
		if(user != null) {
            return user;
        }
        
        throw new UsernameNotFoundException(id + " not found");
	}



    public String getNicknameById(String id) {

		Member member = memberRepository.findById(id).orElseThrow();

		return member.getNickname();
    }

	public Member getSexById(String session) {
		return memberRepository.findById(session).orElseThrow();
	}
}
