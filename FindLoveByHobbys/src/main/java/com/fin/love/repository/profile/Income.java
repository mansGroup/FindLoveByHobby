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
@Table(name = "INCOME")
@SequenceGenerator(name = "INCOME_SEQ_GEN", sequenceName = "INCOME_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCOME_SEQ_GEN")
	private Long incomeId;
	
	@Column(nullable = false)
	private String income;
	
}
