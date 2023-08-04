package com.fin.love.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HobbyService {
	
	public final HobbyRepository hobbyRepository;

	// 취미 리스트
	@Transactional(readOnly = true)
	public List<Hobby> readHobbyList() {
		
		log.info("readHobbyList()");
		
		return hobbyRepository.findAll();
	}
}
