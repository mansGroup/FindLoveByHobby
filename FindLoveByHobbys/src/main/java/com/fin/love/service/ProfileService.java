package com.fin.love.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.profile.dto.ProfileCreateDto;
import com.fin.love.profile.dto.UserHobbyDto;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
	
	public final ProfileRepository profileRepository;
	
	public final UserHobbyRepository userHobbyRepository;
	
	// 컨트롤러에서 받은 클라이언트 정보를 DB에 insert
	public void createProfile(ProfileCreateDto dto, UserHobbyDto hobbyDto) {
		log.info("createProfile(dto= {})", dto);
		
		Profile entity = dto.toEntity();
		log.info("entity= {}", entity);
		
		entity = profileRepository.save(entity);
		log.info("pro= {}", entity);
		
		UserHobby hobbyEntity = hobbyDto.toEntity();
		
		hobbyEntity = userHobbyRepository.save(hobbyEntity);
	}

	
	@Transactional(readOnly = true)
	public Profile read(String user_Id) {
		log.info("read(user_Id={})", user_Id);
		
		return profileRepository.findById(user_Id).orElseThrow();
	}

	
}
