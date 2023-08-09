package com.fin.love.web;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.member.ManagerUserListDto;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.service.ManagerUserListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerUserListController {
	
	private final ManagerUserListService mamagerUserListService;
	private final MemberRepository memberRepository;
	
	@GetMapping("/user/list")
	public String managerUserList(Model model) {
		log.info("managerUserList()");
		
		List<ManagerUserListDto> dtos = new LinkedList<>();
		List<Member> members = memberRepository.findAll();
		
		for (int i = 0; i < members.size(); i++) {
			
			ManagerUserListDto dto = mamagerUserListService.dtoCreate(members.get(i).getId());
			
			dtos.add(dto);
		}
		
		model.addAttribute("members", dtos);
		
		return "/member/manager_user_list";
	}
	
}
