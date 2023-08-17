package com.fin.love.dto.questionRep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestRepModifyDto {

	long replyid;
	long questid;
	
}
