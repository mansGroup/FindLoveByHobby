package com.fin.love.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching")
public class MatchingListRestController {
	
	private final LikeService likeService;
	
	@PostMapping("/likesend/{getterId}/{senderId}")
	public ResponseEntity<Integer> likeSend(
			@PathVariable String userId, 
			@PathVariable String memberId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, memberId);
		
		int result = likeService.likeSend(userId, memberId);
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	
}
