package com.fin.love.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.question.CreateQuestDto;
import com.fin.love.dto.question.UpdateQuestDto;
import com.fin.love.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionservice;
	
	@GetMapping("/qscreate")
	public void qscreate() {
		
		log.info("qscreate()");
		
	}
	
	// TODO 나중에 리스트 페이지 완성 시 변경 필요함.
	@PostMapping("/question")
	public String qscreate(CreateQuestDto dto) {
		
		log.info("qscreate({})",dto);
		
		questionservice.write(dto);
		
		return "redirect:/question/qslist";
		
	}
	
	@GetMapping("/qsmodify")
	public void qsmodify() {
		
		log.info("qsmodify()");
		
	}
	
	@PostMapping("/qsmodify")
	public String qsmodify(UpdateQuestDto dto) {
		
		log.info("qsmodify(update)");
		
		questionservice.update(dto);
		
		return "redirect:/question/qslist";
	}
	
}
