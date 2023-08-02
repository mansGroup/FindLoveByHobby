package com.fin.love.repository.facechat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceReportRepository extends JpaRepository<SpeakChat, Long>{

	SpeakChat findBySpeakRoom(SpeakRoom room);
	
}
