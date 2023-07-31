package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/facechat")
@Controller
@Slf4j
public class FaceChatController {

	// 화상채팅 방으로 입장하는 메서드
	@GetMapping("/room")
	public void facechatroom(Model model, @RequestParam String roomId) {
		
		model.addAttribute("roomId", roomId);
		
		log.info("facechatroom(roomId = {})",roomId);
		
	}
	
	// 임시 입장 메서드
	@GetMapping("/chatroom")
	public void chatroom() {
		
		log.info("chatroom");
		
	}
	
}
