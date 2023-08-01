package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
}
