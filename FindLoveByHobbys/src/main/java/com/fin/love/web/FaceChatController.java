package com.fin.love.web;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/facechat")
@Controller
@Slf4j
public class FaceChatController {

	// 화상채팅 방으로 입장하는 메서드
	@GetMapping("/room")
	public void facechatroom(Model model, @RequestParam String roomId) {

		model.addAttribute("roomId", roomId);

		log.info("facechatroom(roomId = {})", roomId);

	}

	// 임시 입장 메서드
	@GetMapping("/chatroom")
	public void chatroom() {

		log.info("chatroom");

	}

	@PostMapping("/report")
	public String reportFaceChat(Model model, @RequestParam long roomId, @RequestBody MultipartFile audioFile) {

		try {
			// 파일 저장 경로 설정
			String savePath = "/audio/report";
			String fileName = "record-"+ roomId + ".wav";
			File file = new File(savePath, fileName);

			// 파일 저장
			audioFile.transferTo(file);
			log.info("{}",file);
			model.addAttribute("repot",1);
			
			// 서비스 호출해서 리폿 기록
			
			
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("repot",0);
			return "redirect:/landing/index";
			
		}
			
			return "redirect:/landing/index";
			

	}

}
