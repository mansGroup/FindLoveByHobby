package com.fin.love.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.member.ManagerUserListDto;
import com.fin.love.respository.member.Member;
import com.fin.love.service.ManagerUserListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerUserListController {
	
	private final ManagerUserListService mamagerUserListService;
	
	@GetMapping("/user/list")
	public String managerUserList(Model model) {
		
		
		return "/member/manager_user_list";
	}
	
}
