package com.fin.love.repository.question;

import com.fin.love.dto.question.UpdateQuestDto;
import com.fin.love.repository.BaseTimeEntity;
import com.fin.love.respository.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "QUESTION")
@Entity
@SequenceGenerator(allocationSize = 1, name = "QUESTION_SEQ_GEN", sequenceName = "QUESTION_SEQ")
public class Question extends BaseTimeEntity{

	@Id
	@GeneratedValue(generator = "QUESTION_SEQ_GEN")
	private long id;
	@ManyToOne
	@JoinColumn(name="userid") // 참조를 하는 테이블의 참조키를 입력해야 함.
	private Member member;
	@Column
	private String usernickname;
	@Column
	private String useremail;
	@Column
	private int questionoption;
	@Column
	private String questioncontent;
	@Column
	private int status;
	
	public void update(UpdateQuestDto dto) {
		
		this.questioncontent = dto.getContent();
		this.status = dto.getStatus();
	}
	
}
