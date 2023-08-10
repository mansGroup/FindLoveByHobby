package com.fin.love.repository.location;

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

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "LOCATION")
@SequenceGenerator(allocationSize = 1,name = "LOCATION_SEQ_GEN", sequenceName = "LOCATION_SEQ")
public class Location {

	@Id
	@GeneratedValue(generator = "LOCATION_SEQ_GEN", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(nullable = false)
	private String locationname;
	
}
