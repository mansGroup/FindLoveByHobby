package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Academic;
import com.fin.love.repository.profile.AcademicRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademicService {

	private final AcademicRepository academicRepository;
	
	// 학력 리스트
	@Transactional(readOnly = true)
	public List<Academic> readAcademicList() {
		
		log.info("readAcademicList()");
		
		return academicRepository.findAll();
	}
}

