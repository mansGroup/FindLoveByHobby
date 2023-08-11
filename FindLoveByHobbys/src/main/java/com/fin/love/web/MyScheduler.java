package com.fin.love.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Component;

import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.meeting.MeetingRepository;

@Component
public class MyScheduler {

	@Autowired
	private MeetingRepository meetrepository;
	
	// 스케줄러
	// 미팅 시작 시간 1시간 전부터는 참석 혹은 참석취소 불가.
	@Scheduled(fixedRate = 60000)
	public void RefreshStatus() {
		
		
		List<Meeting> list = meetrepository.findAll();
		
		for(Meeting x : list) {
			
			if(x.getStatus()==0) {
				
				LocalDateTime meetdate = x.getMeetingdate().minusHours(1);
				LocalDateTime nowdate = LocalDateTime.now();
				
				int distance = nowdate.compareTo(meetdate);
				
				if(distance>0) {
					
					x.updateStatus(1);
					meetrepository.save(x);
				}
				
			}
			
		}
		
		
	}
	
}
