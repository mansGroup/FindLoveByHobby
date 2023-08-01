package com.fin.love.repository.profile;

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
import lombok.ToString;

@Entity
@Table(name = "ACADEMIC")
@SequenceGenerator(name = "ACADEMIC_SEQ_GEN", sequenceName = "ACADEMIC_SEQ2", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Academic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACADEMIC_SEQ_GEN")
	private Long academicId;
	
	@Column(nullable = false)
	private String academicName;
	
}
