package com.fin.love.web;

import com.fin.love.repository.note.NoteNumber;
import com.fin.love.service.note.NoteNumberService;
import com.fin.love.service.note.NoteService;
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
	private final NoteService noteService;
	private final NoteNumberService noteNumberService;
	
	@PostMapping("/member1/likesend/{userId}/{member1UserId}")
	public ResponseEntity<Integer> member1LikeSend(@PathVariable String userId, @PathVariable String member1UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member1UserId);
		
		int result = likeService.likeSend(userId, member1UserId);
		if(result == 1) {
		noteService.likeSend(userId, member1UserId);
		noteNumberService.upNoteCount(member1UserId);
		noteNumberService.upNoteCount(userId);
		}
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	@PostMapping("/member2/likesend/{userId}/{member2UserId}")
	public ResponseEntity<Integer> member2LikeSend(@PathVariable String userId, @PathVariable String member2UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member2UserId);
		
		int result = likeService.likeSend(userId, member2UserId);
		if(result == 1) {
		noteService.likeSend(userId, member2UserId);
		noteNumberService.upNoteCount(member2UserId);
		noteNumberService.upNoteCount(userId);
		}
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	@PostMapping("/member3/likesend/{userId}/{member2UserId}")
	public ResponseEntity<Integer> member3LikeSend(@PathVariable String userId, @PathVariable String member3UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member3UserId);
		
		int result = likeService.likeSend(userId, member3UserId);
		if(result == 1) {
		noteService.likeSend(userId, member3UserId);
		noteNumberService.upNoteCount(member3UserId);
		noteNumberService.upNoteCount(userId);
		}
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
	@PostMapping("/member4/likesend/{userId}/{member2UserId}")
	public ResponseEntity<Integer> member4LikeSend(@PathVariable String userId, @PathVariable String member4UserId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, member4UserId);
		
		int result = likeService.likeSend(userId, member4UserId);
		if(result == 1) {
		noteService.likeSend(userId, member4UserId);
		noteNumberService.upNoteCount(member4UserId);
		noteNumberService.upNoteCount(userId);
		}
		log.info("result = {}", result);
		
		return ResponseEntity.ok(result); // 0이면 실패, 1이면 성공
	}
	
}
