package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.repository.profile.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	
	public final ProfileRepository profileRepository;

	
}
