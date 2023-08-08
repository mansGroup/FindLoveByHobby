package com.fin.love.service.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.dto.profile.ProfileUpdateDto;
import com.fin.love.dto.profile.UserHobbyDto;
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
	public void createProfile(ProfileCreateDto dto) {
		log.info("createProfile(dto= {})", dto);
		
		Profile entity = dto.toEntity();
		log.info("entity= {}", entity);
		
		entity = profileRepository.save(entity);
		log.info("pro= {}", entity);	
	}
	
	
	@Transactional(readOnly = true)
	public Profile profileModify(String userId) {
		log.info("profileModify(userId={})", userId);
		
		return profileRepository.findById(userId).orElseThrow();
	}
	
	
	@Transactional
	public void profileUpdate(ProfileUpdateDto dto) {
		log.info("profileUpdate(dto={})", dto);
		
		Profile entity = profileRepository.findById(dto.getUserId()).orElseThrow();
		entity.update(dto);
	}
	
	
	@Transactional
	public void profileDelete(String userId) {
		log.info("profileDelete(userId={})", userId);
		
		profileRepository.deleteById(userId);
	}


	
}