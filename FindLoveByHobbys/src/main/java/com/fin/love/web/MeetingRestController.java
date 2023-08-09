package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.dto.meeting.MeetingSearchDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.profile.Age;
import com.fin.love.service.MeetingService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@RestController
@Slf4j
@RequestMapping("/api/meeting")
public class MeetingRestController {

	@Autowired
	private MeetingService meetservice;

	@PostMapping("/search")
	public ResponseEntity<List<Meeting>> search(@RequestBody MeetingSearchDto dto){
		log.info("meeting search(dto = {})",dto);
		List<Meeting> list = new ArrayList<>();
		try {
			list = meetservice.search(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ResponseEntity.ok(list); 
		
	}
	
	@GetMapping("/options/{num}")
	public ResponseEntity<List<?>> options(@PathVariable int num) {
		log.info("options num = {}",num);
		if (num == 0) {
			List<Location> list = meetservice.loadloc();
			return ResponseEntity.ok(list);
			
		} else if (num == 1) {

			List<Hobby> list = meetservice.loadhobby();
			return ResponseEntity.ok(list);
			
		} else {
			
			List<Age> list = meetservice.loadage();
			return ResponseEntity.ok(list);
			
		}

		

	}

}
