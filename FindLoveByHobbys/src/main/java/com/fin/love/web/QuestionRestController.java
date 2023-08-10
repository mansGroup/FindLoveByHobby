package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.dto.question.QuestListReadDto;
import com.fin.love.repository.question.Question;
import com.fin.love.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/quest")
public class QuestionRestController {

	@Autowired
	private QuestionService questservice;
	
	@PostMapping("/list")
	public ResponseEntity<List<Question>> showlist(@RequestBody QuestListReadDto dto) {
		log.info("showlist() dto =  {}",dto);
		List<Question> list2 = questservice.read(dto.getUserid());
		List<Question> list = new ArrayList<>();
		long number = dto.getNum()*3;
		if(number>list2.size()) {
			
			number = list2.size();
			
		}
		
		for(int i = 0 ; i< number ; i++) {
			
			list.add(list2.get(i));
			
		}
		
		
		return ResponseEntity.ok(list);

	}

}
