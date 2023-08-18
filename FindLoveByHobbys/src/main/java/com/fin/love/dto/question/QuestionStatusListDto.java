package com.fin.love.dto.question;

import java.time.LocalDateTime;

import com.fin.love.repository.question.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuestionStatusListDto {
	
	private Long id;
	private String userId;
	private String questionContent;
	private LocalDateTime registeredTime;
	private LocalDateTime completedTime;
	private String status;

	public static QuestionStatusListDto fromEntity(Question question) {
		QuestionStatusListDto dto = QuestionStatusListDto.builder()
				.id(question.getId())
				.userId(question.getMember().getId())
				.questionContent(question.getQuestioncontent())
				.registeredTime(question.getCreatedTime())
				.build();
		
		switch (question.getStatus()) {
		case 0:
			dto.setStatus("처리전");
			break;
		case 1:
			dto.setStatus("처리중");
			break;
		case 2:
			dto.setStatus("답변완료");
			dto.setCompletedTime(question.getModifiedTime());
			break;
		default:
		}
		
		return dto;
	}
	
}
