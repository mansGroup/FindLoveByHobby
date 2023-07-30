package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/facechat")
@Controller
@Slf4j
public class FaceChatController {

	@GetMapping("/room")
	public void facechatroom() {
		
		log.info("facechatroom()");
		
	}
	
}
