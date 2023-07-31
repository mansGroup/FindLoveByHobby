package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/matching")
public class MatchingDetailController {
	
	private final AssessmentRepository assessmentRepository;
	
	@GetMapping("/matchingDetail")
	public void matchingDetail() {
		
	}
	
	@PostMapping("/matchingDetail")
	public void read(Long id, Model model) {
		log.info("matchingdetails(id={})", id);
		
		Assessment assessment = assessmentRepository.read(id);
		
		model.addAttribute("assessment", assessment);
	}
		
}
