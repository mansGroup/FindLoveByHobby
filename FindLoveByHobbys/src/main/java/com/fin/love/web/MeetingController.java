package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.meeting.MeetingMakeDto;
import com.fin.love.dto.meeting.MeetingModifyDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.meetingmember.MeetingMember;
import com.fin.love.repository.profile.Age;
import com.fin.love.service.MeetingService;

import jakarta.servlet.http.HttpSession;
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
	public void meetcreate(Model model) {
		
		List<Hobby> list = meetingservice.loadhobby();
		List<Location> list2 = meetingservice.loadloc();
		
		String iconimg = meetingservice.imageToBase64("C:/IMA/icon.png");
		
		model.addAttribute("list",list);
		model.addAttribute("list2", list2);
		
		model.addAttribute("icon",iconimg);
		
		log.info("meetcreate()");
		
	}
	
	@PostMapping("/create")
	public String meetcreate(MeetingMakeDto dto, HttpSession session) {
		
		log.info("meetmake({})",dto);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String userid = authentication.getName();
		
		dto.setLeader(userid);
		
		meetingservice.create(dto);
		
		
		return "redirect:/meeting/meetinglist?pagenum=1";
		
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam long id, Model model) {
		
		List<Hobby> list = meetingservice.loadhobby();
		List<Location> list2 = meetingservice.loadloc();
		model.addAttribute("list",list);
		model.addAttribute("list2", list2);
		String iconimg = meetingservice.imageToBase64("C:/IMA/icon.png");
		MeetingModifyDto meet = meetingservice.readMyMeeting(id);
		model.addAttribute("icon",iconimg);
		model.addAttribute("meet", meet);
		
	}
	
	@PostMapping("/modify")
	public String modify(MeetingMakeDto dto, @RequestParam long id) {
		log.info("update(dto = {})",dto);
		
		meetingservice.update(dto,id);
		
		
		return "redirect:/meeting/mymeeting";
		
		
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam long deleteId) {
		
		log.info("delete(id = {})",deleteId);
		
		int result = meetingservice.delete(deleteId);
		if(result == 1) {
		log.info("삭제 성공");
		} else {
			log.info("삭제 실패");
			
		}
		return "redirect:/meeting/mymeeting";
		
	}
	
	@GetMapping("/mymeeting")
	public void mylist(HttpSession session, Model model) {
		
		log.info("mymeetinglist()");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String userid = authentication.getName();
		List<Meeting> list = meetingservice.myLeaderList(userid);
		List<Meeting> list2 = meetingservice.myMeetList(userid);
		
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		
	}
	
	@GetMapping("/read")
	public void read(@RequestParam long id, Model model) {
		
		log.info("read(id = {})",id);
		
		MeetingModifyDto meet = meetingservice.readMyMeeting(id);
		model.addAttribute("meet", meet);
		
	}
	
}
