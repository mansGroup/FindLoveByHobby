package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.question.CreateQuestDto;
import com.fin.love.dto.question.UpdateQuestDto;
import com.fin.love.repository.question.Question;
import com.fin.love.respository.member.Member;
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
	@PostMapping("/qscreate")
	public String qscreate(CreateQuestDto dto) {
		
		log.info("qscreate({})",dto);
		
		questionservice.write(dto);
		
		return "redirect:/question/qslist";
		
	}
	
	@GetMapping("/qsmodify")
	public void qsmodify(@RequestParam long numid, Model model) {
		
		log.info("qsmodify()");
		
		Question quest = readAndModify(numid);
		
		model.addAttribute("quest", quest);
	}
	
	@PostMapping("/qsmodify")
	public String qsmodify(UpdateQuestDto dto) {
		
		log.info("qsmodify(update={})",dto);
		
		questionservice.update(dto);
		
		return "redirect:/question/qslist";
	}
	
	@GetMapping("/qslist")
	public void qslist(String userid, Model model) {
		
		log.info("qslist()");
		userid = "희영";
		List<Question> list = questionservice.read(userid);
		
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/qsread")
	public void read(@RequestParam long id, Model model) {
		
		log.info("qsread(id={})",id);
	
		Question quest = readAndModify(id);
		
		model.addAttribute("quest", quest);
		
	}
	
	public Question readAndModify(long id) {
		
		return questionservice.readbyId(id);
		
	}
	
}
