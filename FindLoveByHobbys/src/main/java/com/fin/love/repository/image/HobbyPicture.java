package com.fin.love.repository.image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HOBBYPICTURE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class HobbyPicture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column
	private String hobbyPic1;
	
	@Column
	private String hobbyPic2;
	
	@Column
	private String hobbyPic3;
	
}
