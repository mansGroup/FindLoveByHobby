package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fin.love.dto.facechat.MakeFaceChatRoomDto;

import com.fin.love.dto.facechat.ReportFaceChatDto;
import com.fin.love.respository.member.Member;
import com.fin.love.service.FaceChatService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/facechat")
@Controller
@Slf4j
public class FaceChatController {

	@Autowired
	private FaceChatService faceservice;

	// 화상채팅 방으로 입장하는 메서드
	@PostMapping("/room")
	public String facechatroom(Model model, MakeFaceChatRoomDto dto) {
		log.info("dto = {}", dto);
		
		long roomId = dto.getRoomId();
		int result = faceservice.makeRoom(dto);
		if (result == 0) {
			// 이미 신고된 방이라 연결이 안되는 경우 다시 채팅방으로. 주소는 나중에 수정 필요 TODO
			return "redirect:/facechat/chatroom";

		}
		List<Member> list = faceservice.loadMemberName(dto);

		for (Member x : list) {

			if (x.getId().equals(dto.getSpeakmember1())) {

				model.addAttribute("speakmember1", x.getName());

			} else {

				model.addAttribute("speakmember2", x.getName());

			}

		}

		model.addAttribute("roomId", roomId);

		log.info("facechatroom(roomId = {})", roomId);
		return "/facechat/room";
	}

	// 임시 입장 메서드
	@GetMapping("/chatroom")
	public void chatroom() {

		log.info("chatroom({})");

	}

	// 신고 처리 메서드
	@PostMapping("/report")
	public String facechatreport(ReportFaceChatDto dto) {

		faceservice.doReport(dto);

		return "redirect:/";

	}

}
