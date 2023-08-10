package com.fin.love.repository.assessment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER_ASSESSMENT")
@SequenceGenerator(name = "USERID_SEQ_GEN", sequenceName = "USER_ASSESSMENT_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class UserAssessment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERID_SEQ_GEN")
	private Long id;
	
	@Column(nullable = true)
	private String sender;
	
	@Column(nullable = true)
	private String getter;
}
