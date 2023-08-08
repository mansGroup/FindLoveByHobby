package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerUserListController {
	
	@GetMapping("/user/list")
	public String managerUserList() {
		
		
		return "/member/manager_user_list";
	}
	
}
