package com.fin.love.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.service.AnnouncementEventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/event")
public class AnnouncementEventController {
	
	private final AnnouncementEventService announcementEventService;
	
	@GetMapping("/announcementEvent")
	public String announcementEvent(
			Long id,
			AnnouncementEventDto dto, 
			Model model) {
		
		// 포스트 목록 검색:
//        List<AnnouncementEvent> list = announcementEventService.read();
//        // Model에 검색 결과를 세팅:
//        model.addAttribute("event", list);
        
        
        // form에서 submit(제출)된 내용을 DB 테이블에 insert
        announcementEventService.create(dto);
        
        
//        // POSTS 테이블에서 id에 해당하는 포스트를 검색.
//        AnnouncementEvent AEvent = announcementEventService.read(id);
//        // 결과를 model에 저장 -> 뷰로 전달됨.
//        model.addAttribute("event", AEvent);
//
//        // postService를 이용해서 DB 테이블에서 포스트를 삭제하는 서비스 호출:       
//        announcementEventService.delete(id);
//        
//        // 포스트 업데이트 서비스 호출:
//        announcementEventService.update(dto);

        
        return "/event/announcementEvent";
	}
	
	
	
//	// 테스트 페이지로 이동하는 메서드
//	@GetMapping("/eventTest")
//	public void eventTest(Model model) {
//		String code = "a";
//		model.addAttribute("id", code);
//	}

}
