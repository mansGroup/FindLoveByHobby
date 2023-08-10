package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Smoker;
import com.fin.love.repository.profile.SmokerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmokerService {
	
	private final SmokerRepository smokerRepository;
	
	// 흡연 유무 리스트
	@Transactional(readOnly = true)
	public List<Smoker> readSmokerList() {
		
		log.info("readSmokerList()");
		
		return smokerRepository.findAll();
	}

}
