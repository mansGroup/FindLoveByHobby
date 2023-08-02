package com.fin.love.repository.image;

import com.fin.love.repository.like.Like;

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
@Table(name = "PICTURE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column
	private String pic1;
	
	@Column
	private String pic2;
	
	@Column
	private String pic3;
	
	@Column
	private String pic4;
	
	@Column
	private String pic5;
	
}
