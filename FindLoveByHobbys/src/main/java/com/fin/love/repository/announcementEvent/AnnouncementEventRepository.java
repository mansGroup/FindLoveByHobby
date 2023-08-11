package com.fin.love.repository.announcementEvent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementEventRepository extends JpaRepository<AnnouncementEvent, Long>{
	
	List<AnnouncementEvent> findByOrderByIdDesc();
	
}
