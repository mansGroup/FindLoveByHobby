package com.fin.love.repository.ann;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.repository.announcementEvent.AnnouncementEventRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AnnouncememntEventFindAllTest {
	
	@Autowired
	private AnnouncementEventRepository aer;
	
	@Test
	public void test() {
		log.info("시작");
		
		List<AnnouncementEvent> list = aer.findByOrderByIdDesc();
		
		for (int i = 0; i < list.size(); i++) {
			log.info("확인 >>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + list.get(i));
		}
		
		log.info("종료");
	}
	
}
