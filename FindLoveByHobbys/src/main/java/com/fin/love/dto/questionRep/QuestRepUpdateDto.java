package com.fin.love.dto.questionRep;

import com.fin.love.repository.questreply.QuestionReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestRepUpdateDto {

	private long replyid;
	
	private String repmessage;
	
	public QuestionReply toEntity() {
		
		return QuestionReply.builder().replyid(replyid).replycontent(repmessage).build();
				
		
	}
	
}
