package com.fin.love.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateQuestDto {

	private long numid;
	private String userid;
	private String content;
	private int status;
	
}
