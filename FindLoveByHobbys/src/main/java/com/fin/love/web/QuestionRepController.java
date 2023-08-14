package com.fin.love.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.questionRep.QuestRepCreatetDto;
import com.fin.love.repository.question.Question;
import com.fin.love.repository.questreply.QuestionReply;
import com.fin.love.service.QuestReplyService;
import com.fin.love.service.QuestionService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/questionrep")
public class QuestionRepController {
	
	@Autowired
	private QuestReplyService questReplyService;
	@Autowired
	private QuestionService questionService;
	
	

	@GetMapping("/qrepcreate")
	public String qrepcreate(QuestRepCreatetDto dto) {
		
		questReplyService.create(dto);
		
		log.info("qrepcreate()",dto);
		
		
		
		return "redirect:/questionrep/qreplist";
	}
	
	@GetMapping("/qreplist")
	public void qreplist(Model model) {
		
		log.info("qreplist()");
		
		List<Question> list = questReplyService.read();
		
		model.addAttribute("list", list);

	}
	
}
