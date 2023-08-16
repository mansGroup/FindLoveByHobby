package com.fin.love.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.love.dto.questionRep.QuestRepCreatetDto;
import com.fin.love.dto.questionRep.QuestRepListDto;
import com.fin.love.dto.questionRep.QuestRepUpdateDto;
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
	
	
	public List<QuestRepListDto> read() {
		log.info("read=()");
		
		 List<Question> list2 = questionRepository.findAll();
		 List<QuestRepListDto> list = new ArrayList<>();
		 for(Question x : list2) {
			 
			 QuestionReply reply = questionRepRepository.findByQuestionId(x.getId());
			 
			 if(reply == null) {
				 
				 reply = new QuestionReply();
				 
			 }
			 
			 QuestRepListDto dto = QuestRepListDto.builder().reply(reply).quest(x).build();
			 log.info("xId = {}",x.getId());
			 log.info("reply = {}",reply.getModifiedTime());
			 list.add(dto);
		 }
		 
//		 log.info("listsize=({})",list.size());
		
		return list;
	}


	public QuestionReply create(QuestRepCreatetDto dto) {
		
		log.info("save(dto={})", dto);
		Question qu = questionRepository.findById(dto.getQuestid()).orElseThrow();
		
		QuestionReply qr = dto.toEntity(qu);
		
		return questionRepRepository.save(qr);
	
	}
	
//	public List<QuestionReply> findAll() {
//		
//		List<QuestionReply> list = questionRepRepository.findAll();
//		return list;
//	}

	public QuestionReply findbyQuestionId(long id) {
		// TODO Auto-generated method stub
		return questionRepRepository.findByQuestionId(id);
	}


	public void delete(Long id) {
		log.info("delete(id={})", id);
		
		questionRepRepository.deleteById(id);
	}


	public void update(QuestRepUpdateDto dto) {
		// TODO Auto-generated method stub
		
		QuestionReply reply = questionRepRepository.findById(dto.getReplyid()).orElseThrow();
		
		reply.updateContent(dto);
		
		questionRepRepository.save(reply);
		
		
	}

}
