package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Religion;
import com.fin.love.repository.profile.ReligionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReligionService {

	private final ReligionRepository religionRepository;
	
	// 종교 리스트
	@Transactional(readOnly = true)
	public List<Religion> readReligionList() {
		
		log.info("readReligionList()");
		
		return religionRepository.findAll();
	}
}
