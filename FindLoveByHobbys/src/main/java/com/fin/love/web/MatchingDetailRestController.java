package com.fin.love.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.service.MatchingDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matchingDetail")

public class MatchingDetailRestController {
	
	private final MatchingDetailService detailService;
	
	@GetMapping("/assessment/{memberId}")
	public ResponseEntity<Integer> assessment(
			@PathVariable String memberId) {
		log.info("assessment(memberId = {})", memberId);
		
//		int result = detailService.assessment(memberId);
//		log.info("result = {}", result);

		return ResponseEntity.ok(null);
	}
}
