package com.fin.love.repository.questreply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.love.repository.question.Question;
import com.fin.love.respository.member.Member;

public interface QuestionRepRepository extends JpaRepository<QuestionReply, Long>{

	QuestionReply findByQuestionId(long id);
	
}

