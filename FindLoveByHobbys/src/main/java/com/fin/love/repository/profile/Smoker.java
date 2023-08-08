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
@Table(name = "SMOKER")
@SequenceGenerator(name = "SMOKER_SEQ_GEN", sequenceName = "SMOKER_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Smoker {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMOKER_SEQ_GEN")
	private String smokerId;
	
	@Column(nullable = false)
	private String smokerName;

}
