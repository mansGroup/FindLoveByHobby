package com.fin.love.dto.questionRep;

import com.fin.love.repository.question.Question;
import com.fin.love.repository.questreply.QuestionReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestRepListDto {

	private Question quest;
	
	private QuestionReply reply;
	
}
