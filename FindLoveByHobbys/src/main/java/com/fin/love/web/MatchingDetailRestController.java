package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.service.MatchingDetailService;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matchingDetail")

public class MatchingDetailRestController {
	
	@Value("${com.example.eventUpload.path}") // application.properties의 변수
	private String eventUploadPath;
	
	private final MatchingDetailService detailService;
	private final PictureService pictureService;

	
	
	
	
	@PostMapping("/assessment/{assessmentName}/{senderId}/{getterId}")
	public ResponseEntity<Integer> assessment(
			@PathVariable String assessmentName,
			@PathVariable String senderId, 
			@PathVariable String getterId) {
		log.info("assessment(senderId = {}, getterId = {})", getterId, senderId);
		
		int count = 0;
		
		Assessment assessment = detailService.findById(getterId);
		log.info("assessment = {}", assessment);
		
		if (assessmentName.equals("sexy")) {
		    detailService.sexyCountUp(getterId);
		    count = assessment.getSexy();
		    
		} 
		
		if (assessmentName.equals("beautiful")) {
		    detailService.beautifulCountUp(getterId);
		    count = assessment.getBeautiful();
		} 
		
		if (assessmentName.equals("cute")) {
		    detailService.cuteCountUp(getterId);
		    count = assessment.getCute();		    
		} 
		
		if (assessmentName.equals("wonderful")) {
		    detailService.wonderfulCoubtUp(getterId);
		    count = assessment.getWonderful();	    
		} 
		
		if (assessmentName.equals("handsome")) { 
		    detailService.handsomeCountUp(getterId);
		    count = assessment.getHandsome();
		}


	return ResponseEntity.ok(count);

	}

	// 호감 보낼 때 중복체크하는 코드
	@PostMapping("/assessment/chack/{senderId}/{getterId}")
	public ResponseEntity<Integer> chack(
			@PathVariable String senderId, 
			@PathVariable String getterId) {
		log.info("chack(setterId = {}, getterId = {})", senderId, getterId);
		
		int result = detailService.assessment(senderId, getterId);
		log.info("result = {}", result);

		return ResponseEntity.ok(result);
	}

}
