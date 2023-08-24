package com.fin.love.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHobbyRepository extends JpaRepository<UserHobby, String> {
	
	List<UserHobby> findByUserid(String id);
	
	void deleteByUserid(String userid);
	
	List<UserHobby> findByHobbyId(Long hobbyId);
	
}
