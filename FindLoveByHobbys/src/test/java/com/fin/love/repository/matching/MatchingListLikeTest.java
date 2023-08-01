package com.fin.love.repository.matching;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.repository.like.Like;
import com.fin.love.repository.like.LikeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MatchingListLikeTest {
	
	@Autowired
	private LikeRepository lr;
	
	@Test
	public void findTest() {
		String sender = "qwer12341";
		String recipient = "asdf1234";
		
		Like like = lr.findBySenderAndRecipient(sender, recipient);
		log.info("like >>>>> " + like);
		
		Long num = like.getId();
		
		log.info("like Id >>>>> " + num);
		
		
		
	}
	
}
