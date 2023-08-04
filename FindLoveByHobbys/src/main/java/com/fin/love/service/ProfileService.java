package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.profile.dto.ProfileCreateDto;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
	
	public final ProfileRepository profileRepository;
	
	// 컨트롤러에서 받은 클라이언트 정보를 DB에 insert
	public void createProfile(ProfileCreateDto dto) {
		log.info("createProfile(dto= {})", dto);
		
		Profile entity = dto.toEntity();
		log.info("entity= {}", entity);
		
		entity = profileRepository.save(entity);
		log.info("pro= {}", entity);
	}
	
	
	
	// 사용자 기본 정보 읽기
//	public ProfileReadUserInfoDto readMemberInfo(String name) {
//		log.info("read(name={})", name);
//		
//		Member entity = profileRepository.findAll(name);
//		log.info("findName");
//		
//		ProfileReadUserInfoDto dto = ProfileReadUserInfoDto.fromEntity(entity);
//		log.info("findDto");
//		
//		return dto;
//	}


	
}
