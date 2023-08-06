package com.fin.love.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.repository.like.LikeRepository;
import com.fin.love.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching")
public class MatchingListRestController {
	
	private final LikeService likeService;
	
	@PostMapping("/member1/likesend/{userId}/{member1UserId}")
	public ResponseEntity<Integer> member1LikeSend(@PathVariable String userId, @PathVariable String member1UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member1UserId);
		
		int result = likeService.likeSend(userId, member1UserId);
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	@PostMapping("/member2/likesend/{userId}/{member2UserId}")
	public ResponseEntity<Integer> member2LikeSend(@PathVariable String userId, @PathVariable String member2UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member2UserId);
		
		int result = likeService.likeSend(userId, member2UserId);
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	
}
