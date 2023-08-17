package com.fin.love.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.love.dto.question.CreateQuestDto;
import com.fin.love.dto.question.UpdateQuestDto;
import com.fin.love.repository.question.Question;
import com.fin.love.repository.question.QuestionRepository;
import com.fin.love.repository.questreply.QuestionReply;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionService {

	@Autowired
	private QuestionRepository questrepository;

	@Autowired
	private MemberRepository memberrepository;
	
	public void write(CreateQuestDto dto) {
		
		log.info("save(dto={})",dto);
		Member user = memberrepository.findById(dto.getUserid()).orElseThrow();
		Question quest = dto.toEntity(user);
		
		questrepository.save(quest);
		
	}

	
	public void update(UpdateQuestDto dto) {
		// TODO Auto-generated method stub
		log.info("updateRow({})",dto);
		
		Question quest = questrepository.findById(dto.getNumid()).orElseThrow();
		
		quest.update(dto);
		
		questrepository.save(quest);
		
	}
	
	public List<Question> read(String userid){
		
		Member member = memberrepository.findById(userid).orElseThrow();
		
		log.info("readlist({})",userid);
		//TODO 유저아이디가 아닌 MEMBER 객체로 찾기
		return questrepository.findByMemberOrderById(member);
		
	}


	public Question readbyId(long id) {
		// TODO Auto-generated method stub
		
		return questrepository.findById(id).orElseThrow();
	}
	
	 public void updateStatusTo1(long id) {
	        Question question = questrepository.findById(id).orElseThrow();
	        question.setStatus(1);
	        questrepository.save(question);
	    }
	
	public Member readbyUserId(String userid) {
		
		return memberrepository.findById(userid).orElseThrow();
		
	}


	public void updateStatusTo2(Long id) {
		Question question = questrepository.findById(id).orElseThrow();
        question.setStatus(2);
        questrepository.save(question);
		
	}


	
	
	
}
