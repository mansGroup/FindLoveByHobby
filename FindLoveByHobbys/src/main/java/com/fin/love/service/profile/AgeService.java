package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgeService {
	
	private final AgeRepository ageRepository;

	// 나이 리스트
	@Transactional(readOnly = true)
	public List<Age> readAgeList() {
		
		log.info("readAgeList()");
		
		return ageRepository.findAll();
	}
}
