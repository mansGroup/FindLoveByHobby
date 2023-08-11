package com.fin.love.repository.profile;

import com.fin.love.dto.profile.UserHobbyUpdateDto;

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
@Table(name = "USER_HOBBY")
@SequenceGenerator(name = "USER_HOBBY_SEQ_GEN", sequenceName = "USER_HOBBY_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class UserHobby {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_HOBBY_SEQ_GEN")
	private Long id;
	
	@Column(nullable = false)
	private Long hobbyId;
	
	@Column(nullable = false)
	private String userid;
	
	
	public UserHobby updateData(Long id, String userid, Long hobbyId) {
		this.id = id;
		this.userid = userid;
		this.hobbyId = hobbyId;
		
		return this;
	}
	
	
	public UserHobby userHobbyUpdate(UserHobbyUpdateDto dto) {
		this.id = dto.getId();
		this.userid = dto.getUserid();
		this.hobbyId = dto.getHobbyId();
		
		return this;
	}
	
}
