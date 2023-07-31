package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.repository.profile.UserHobbyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingService {
	
	private final UserHobbyRepository userHobbyRepository;
	
	
	
}
