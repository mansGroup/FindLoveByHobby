package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Drings;
import com.fin.love.repository.profile.DringsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DringsService {
	
	private final DringsRepository dringsRepository;
	
	// 음주 유무 리스트
	@Transactional(readOnly = true)
	public List<Drings> readDringsList() {
		
		log.info("readDringsList()");
		
		return dringsRepository.findAll();
	}

}
