package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.meeting.MeetingMakeDto;
import com.fin.love.repository.meeting.Meeting;
import com.fin.love.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/meeting")
public class MeetingController {
	
	@Autowired
	private MeetingService meetingservice;
	
	@GetMapping("/meetinglist")
	public void readlist(@RequestParam int pagenum, Model model) {
		
		log.info("meetinglist()");
		
		List<Meeting> list = meetingservice.makelist(pagenum);
		
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/create")
	public void meetcreate() {
		
		log.info("meetcreate()");
		
	}
	
	@PostMapping("/create")
	public String meetcreate(MeetingMakeDto dto) {
		
		log.info("meetmake({})",dto);
		
		meetingservice.create(dto);
		
		return "redirect:/meeting/meetinglist";
		
	}
	
}
