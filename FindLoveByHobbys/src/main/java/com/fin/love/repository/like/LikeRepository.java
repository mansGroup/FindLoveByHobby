package com.fin.love.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
	Like findBySenderAndRecipient(String sender, String recipient);
	
}
