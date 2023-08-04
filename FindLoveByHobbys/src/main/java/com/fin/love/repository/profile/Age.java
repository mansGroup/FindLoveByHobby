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
@Table(name = "AGE")
@SequenceGenerator(name = "AGE_SEQ_GEN", sequenceName = "AGE_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Age {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGE_SEQ_GEN")
	private Long ageId;
	
	@Column(nullable = false)
	private String ageName;

}
