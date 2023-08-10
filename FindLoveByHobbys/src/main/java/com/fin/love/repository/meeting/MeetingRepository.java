package com.fin.love.repository.meeting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{

	List<Meeting> findByLeader(String leader);
	
}
