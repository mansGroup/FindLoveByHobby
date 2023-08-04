package com.fin.love.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHobbyRepository extends JpaRepository<UserHobby, Long> {
	
	List<UserHobby> findByUserid(String id);
	
	
}
