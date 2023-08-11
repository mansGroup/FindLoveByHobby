package com.fin.love.service;

import org.springframework.stereotype.Service;

import com.fin.love.repository.like.Like;
import com.fin.love.repository.like.LikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
	
	private final LikeRepository likeRepository;

	public int likeSend(String userId, String memberId) {
		log.info("likeSend(userId = {}, memberId = {})", userId, memberId);
		
		int result = 0; // 0이면 실패, 1이면 성공
		
		Like like = likeRepository.findBySenderAndRecipient(userId, memberId);
		
		if (like == null) {
			result = 1;
			
			Like entity = Like.builder().sender(userId).recipient(memberId).whether(0).build();
			likeRepository.save(entity);
		}
		
		return result;
	}

	@Transactional
	public void chageWhether(String senderId, String recipientId, int i) {
		Like like = likeRepository.findBySenderAndRecipient(senderId, recipientId);
		like.chageWhether(i);
	}
}
