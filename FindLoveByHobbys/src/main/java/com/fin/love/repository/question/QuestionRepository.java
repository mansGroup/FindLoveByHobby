package com.fin.love.repository.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fin.love.repository.questreply.QuestionReply;
import com.fin.love.respository.member.Member;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByMemberOrderById(Member member);

	
	
}
