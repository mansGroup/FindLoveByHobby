package com.fin.love.wed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingDetailController {
	
	@GetMapping("/matchingDetail")
	public void matchingDetail() {
		log.info("matchingDetail() GET");
	}

}
