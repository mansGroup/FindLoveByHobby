package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerUserListRestController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
	@PostMapping("/user/list/{sorting}")
	public ResponseEntity<List<Member>> userListSorting(@PathVariable String sorting) {
		log.info("userListSorting(sorting = {})", sorting);
		
		List<Member> members = new ArrayList<>();
		
		if (sorting.equals("basic")) {
			members = memberRepository.findByOrderByIdDesc();
		} else if (sorting.equals("nameUp")) {
			members = memberRepository.findByOrderByName();
		} else if (sorting.equals("nameDown")) {
			members = memberRepository.findByOrderByNameDesc();
		} else if(sorting.equals("nicknameUp")) {
			members = memberRepository.findByOrderByNickname();
		} else if (sorting.equals("nicknameDown")) {
			members = memberRepository.findByOrderByNicknameDesc();
		} else if (sorting.equals("genderMan")) {
			members = memberRepository.findByOrderBySex();
		} else if (sorting.equals("genderWoman")) {
			members = memberRepository.findByOrderBySexDesc();
		} else if (sorting.equals("roleUp")) {
			members = memberRepository.findByOrderByRole();
		} else if (sorting.equals("roleDown")) {
			members = memberRepository.findByOrderByRoleDesc();
		}
		
		return ResponseEntity.ok(members);
	}
	
}
