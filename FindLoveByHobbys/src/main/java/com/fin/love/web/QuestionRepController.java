package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.question.QuestionStatusListDto;
import com.fin.love.dto.questionRep.QuestRepCreatetDto;
import com.fin.love.dto.questionRep.QuestRepModifyDto;
import com.fin.love.dto.questionRep.QuestRepUpdateDto;
import com.fin.love.dto.questionRep.QuestRepListDto;
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
	@Autowired
	private QuestionService questionservice;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/qrepcreate")
	public void qrepcreate(@RequestParam long id, Model model) {
		log.info("id=({})", id);

		Question quest = readAndModify(id);
		questionService.updateStatusTo1(id);

		String role = quest.getMember().getRole().toString();
		log.info("role=({})", role);
		int roles = 0;
		switch (role) {

		case "USER":
			roles = 0;
			break;
		case "UNVARIFIED_USER":
			roles = 1;
			break;
		case "RIP_USER":
			roles = 2;
			break;
		case "ADMIN":
			roles = 3;
			break;

		}
		QuestionReply reply = questReplyService.findbyQuestionId(id);

//		if(reply == null) {
//			reply.setReplycontent("답변을 등록하세요");
//			
//		} 
		log.info("qreply=({})", reply);

		model.addAttribute("reply", reply);
		log.info("roles=({})", roles);
		model.addAttribute("quest", quest);
		model.addAttribute("role", roles);
	}

	public Question readAndModify(long id) {

		return questionservice.readbyId(id);

	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/qrepcreate")
	public String qrepcreate(QuestRepCreatetDto dto) {

		questReplyService.create(dto);

		Long id = dto.getQuestid();

		questionService.updateStatusTo2(id);
		log.info("qrepcreate()", dto);

		return "redirect:/questionrep/qreplist";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/qreplist")
	public void qreplist(Model model, QuestRepListDto dto) {
		log.info("qreplist()");
		List<QuestRepListDto> list = questReplyService.read();
		
		List<QuestionStatusListDto> list2 = questionservice.readAll();
		log.info(list2.toString());
		
		
		
		model.addAttribute("list2",list2);
		model.addAttribute("list", list);

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/qrepmodify")
	public void modify(@RequestParam long id, Model model) {
		log.info("GET()");

		Question quest = readAndModify(id);

		String role = quest.getMember().getRole().toString();
		log.info("role=({})", role);
		int roles = 0;
		switch (role) {

		case "USER":
			roles = 0;
			break;
		case "UNVARIFIED_USER":
			roles = 1;
			break;
		case "RIP_USER":
			roles = 2;
			break;
		case "ADMIN":
			roles = 3;
			break;

		}
		QuestionReply reply = questReplyService.findbyQuestionId(id);

//		if(reply == null) {
//			reply.setReplycontent("답변을 등록하세요");
//			
//		} 
		log.info("qreply=({})", reply);

		model.addAttribute("reply", reply);
		log.info("roles=({})", roles);
		model.addAttribute("quest", quest);
		model.addAttribute("role", roles);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/qrepmodify")
	public String delete(QuestRepModifyDto dto) {
		log.info("id=()",dto.getReplyid());
		log.info("questionId=()", dto.getQuestid());
		
		questReplyService.delete(dto.getReplyid());
		log.info("delete={}", dto.getReplyid());
		questionService.updateStatusTo1(dto.getQuestid());
		return "redirect:/questionrep/qreplist";

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public String update(QuestRepUpdateDto dto) {
		log.info("dto = {}", dto);
		questReplyService.update(dto);
		
		return "redirect:/questionrep/qreplist";
	}
}
