package com.fin.love.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.service.AnnouncementEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/announcementEvent")
public class AnnouncementEventController {

	private final AnnouncementEventService announcementEventService;
	
	
	
	@GetMapping("/eventMain")
	public void read(Model model) {
		log.info("post()");

		// 포스트 목록 검색:
		List<AnnouncementEvent> list = announcementEventService.read();
		log.info("list={}", list);
		model.addAttribute("announcemnetEvent", list);
		// Model에 검색 결과를 세팅:

	}

	// 작성하기 들어가는 페이지
	@GetMapping("/eventCreate")
	public void announcementEvent() {
		log.info("create() GET");

	}

	// 작성한 정보를 DB에 넣기위해
	@PostMapping("/eventCreate")
	public String create(Long id, AnnouncementEventDto dto) {
		log.info("create(dto={}) POST", dto);

		announcementEventService.create(dto);

		return "redirect:/announcementEvent/eventMain";
	}

	// "/eventDetails", "/eventModify" 요청 주소들을 처리하는 컨트롤러 메서드
	@GetMapping({ "/eventDetails", "/eventModify", "/eventUser" })
	public void read(Long id, Model model) {
		log.info("read(id={}", id);

		// ANNOUNCEMENT_EVENT 테이블에서 id에 해당하는 포스트를 검색.
		AnnouncementEvent post = announcementEventService.read(id);

		// 결과를 model에 저장.
		model.addAttribute("post", post);

		// 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우),
		// 뷰의 이름은 요청 주소와 같다!
		// details -> details.html, modify -> modify.html
	}

	// DB 행을 삭제
	@PostMapping("/eventDelete")
	public String delete(Long id) {
		log.info("delete(id={})", id);

		// postService를 이용해서 DB 테이블에서 포스트를 삭제하는 서비스 호출:
		announcementEventService.delete(id);

		return "redirect:/announcementEvent/eventMain";
	}

	// DB 행을 업데이트
	@PostMapping("/eventUpdate")
	public String update(AnnouncementEventDto dto) {
		log.info("update={}", dto);

		announcementEventService.update(dto);
		log.info("업데이트 결과 ={}", dto);

		return "redirect:/announcementEvent/eventDetails?id=" + dto.getId();
		// 쿼리스트링에선 공백이 있으면 안된다.
	}
	

}
