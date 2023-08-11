package com.fin.love.repository.questreply;

import com.fin.love.repository.BaseTimeEntity;
import com.fin.love.repository.question.Question;

import groovy.transform.ToString;
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

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "QUESTIONREPLY")
@Entity
@SequenceGenerator(allocationSize = 1, name = "QUESTIONREPLY_SEQ_GEN", sequenceName = "QUESTIONREPLY_SEQ")
public class QuestionReply extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "QUESTIONREPLY_SEQ_GEN")
	private long replyid;
	@ManyToOne
	@JoinColumn(name="questionid")
	private Question question;
	@Column
	private String replycontent;
	
	
}
