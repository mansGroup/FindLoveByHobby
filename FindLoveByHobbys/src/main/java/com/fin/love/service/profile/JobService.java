package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Jobs;
import com.fin.love.repository.profile.JobsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

	private final JobsRepository jobsRepository;
	
	// 직업 리스트
	@Transactional(readOnly = true)
	public List<Jobs> readJobsList() {
		
		log.info("readJobsList()");
		
		return jobsRepository.findAll();
	}
	
	
}
