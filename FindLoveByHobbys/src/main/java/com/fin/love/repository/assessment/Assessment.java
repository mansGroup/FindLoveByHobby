package com.fin.love.repository.assessment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ASSESSMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Assessment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; // PK
	
	@Column(nullable = true)
	private int sexy; 
	
	@Column(nullable = true)
	private int beautiful;
	
	@Column(nullable = true)
	private int cute;
	
	@Column(nullable = true)
	private int handsome;
	
	@Column(nullable = true)
	private int wonderful;
	
}
