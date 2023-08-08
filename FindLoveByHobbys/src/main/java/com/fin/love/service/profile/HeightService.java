package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.HeightRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeightService {

	private final HeightRepository heightRepository;
	
	// 키 리스트
	@Transactional(readOnly = true)
	public List<Height> readHeightList() {
		log.info("readHeightList()");
		
		return heightRepository.findAll();
	}
}
