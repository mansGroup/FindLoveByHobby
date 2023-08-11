package com.fin.love.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.love.repository.question.Question;
import com.fin.love.repository.question.QuestionRepository;
import com.fin.love.repository.questreply.QuestionRepRepository;
import com.fin.love.repository.questreply.QuestionReply;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestReplyService {
	
	@Autowired 
	private QuestionRepRepository questionRepRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	
	public List<Question> read() {
		log.info("read=()");
		
		 List<Question> list = questionRepository.findAll();
		 log.info("listsize=({})",list.size());
		
		return list;
	}

}
