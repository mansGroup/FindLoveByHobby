package com.fin.love.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.member.ManagerUserListDto;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;
import com.fin.love.service.ManagerUserListService;
import com.fin.love.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerUserListController {
	
	private final ManagerUserListService mamagerUserListService;
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	
	@GetMapping("/user/list/{pageCount}")
	public String managerUserList(@PathVariable int pageCount, Model model) {
		log.info("managerUserList()");
		
		Role[] roles = Role.values();
		List<ManagerUserListDto> dtos = new LinkedList<>();
		List<Member> members = memberRepository.findByOrderByRoleDesc();
		
		for (int i = 0; i < members.size(); i++) {
			ManagerUserListDto dto = mamagerUserListService.dtoCreate(members.get(i).getId());
			
			dtos.add(dto);
		}
		
		model.addAttribute("role", roles);
		model.addAttribute("members", dtos);
		
		// 페이지 네이션
		int onePageFirstCount = 1;
		int onePageMaxCount = 5;
		
		model.addAttribute("onePageFirstCount", onePageFirstCount);
		model.addAttribute("onePageMaxCount", onePageMaxCount);
		model.addAttribute("pageCount", pageCount);
		
		return "/member/manager_user_list";
	}
	
	@PostMapping("/role/update/{userId}")
	public String roleUpdate(@PathVariable String userId) {
		log.info("roleUpdate(userId = {})", userId);
		
		memberService.updateRole(userId);
		
		return "redirect:/manager/user/list/1";
	}
	
}
