package com.fin.love.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MatchingDetailService {
	
	// 생성자를 사용한 의존성 주입:
	private final AssessmentRepository assessmentRepository;
	
	// DB 테이블에서 id 불러오기
    @Transactional(readOnly = true)
	public Assessment read(Long id) {
		log.info("read(id={})", id);
		
	    return assessmentRepository.findById(id).orElseThrow();
	}
    
    
}
