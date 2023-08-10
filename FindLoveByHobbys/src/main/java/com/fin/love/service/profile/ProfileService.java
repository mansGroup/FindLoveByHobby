package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.dto.profile.ProfileSearchDto;
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
	
	
//	@Transactional
//	public List<Profile> search(ProfileSearchDto dto) {
//		log.info("search(dto= {})", dto);
//		
//		List<Profile> list = null;
//		switch(dto.getType()) {
//		
//		}
//		
//	}

}
