package com.fin.love.dto.question;

import com.fin.love.repository.question.Question;
import com.fin.love.respository.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestDto {

	private String userid;
	private String useremail;
	private String questioncontent;
	private int questionoption;
	
	
	public Question toEntity(Member member) {
		
		return Question.builder().member(member).questioncontent(questioncontent).questionoption(questionoption).useremail(member.getEmail()).usernickname(member.getNickname()).build();
		
	}
	
}
