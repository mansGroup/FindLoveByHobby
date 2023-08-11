package com.fin.love.repository.matching;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.dto.matching.MatchingDetailDto;
import com.fin.love.repository.assessment.AssessmentRepository;
import com.fin.love.repository.like.Like;
import com.fin.love.repository.like.LikeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MatchingListLikeTest {
	
	@Autowired
	private LikeRepository lr;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
//	@Test
//	public void findHobby() {
//		
//		List<MatchingDetailDto> mat = assessmentRepository.findByHobbyise("a");
//		log.info("{}",mat);
//	}
	
	
//	@Test
	public void findTest() {
		String sender = "qwer12341";
		String recipient = "asdf1234";
		
		Like like = lr.findBySenderAndRecipient(sender, recipient);
		log.info("like >>>>> " + like);
		
		Long num = like.getId();
		
		log.info("like Id >>>>> " + num);
		
		
		
	}
	
}
