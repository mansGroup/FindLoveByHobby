package com.fin.love.dto.questionRep;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.question.Question;
import com.fin.love.repository.questreply.QuestionReply;

import lombok.Data;

@Data
public class QuestRepCreatetDto {
	
	private Long questid;
	private String repmessage;
	
	public QuestionReply toEntity(Question question) {
		return QuestionReply.builder()
				.replycontent(repmessage)
				.question(question)
				.build();
				
				
	}
}
