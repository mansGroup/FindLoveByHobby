package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.profile.Age;
import com.fin.love.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/meeting")
public class MeetingRestController {

	@Autowired
	private MeetingService meetservice;

	@GetMapping("/options")
	public ResponseEntity<List<?>> options(int num) {

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
