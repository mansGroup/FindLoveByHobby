package com.fin.love.repository.announcementEvent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementEventRepository extends JpaRepository<AnnouncementEvent, Long>{
	
	// id 내림차순 정렬:
    // select * from POSTS order by ID desc
	List<AnnouncementEvent> findByOrderByIdDesc();
}
