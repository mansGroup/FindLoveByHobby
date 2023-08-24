package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fin.love.dto.facechat.ReportListDto;
import com.fin.love.dto.facechat.ReportReadDto;
import com.fin.love.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class adminReportRestContorller {

	@Autowired
	private AdminService adminservice;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/refresh")
	public ResponseEntity<List<ReportReadDto>> listrefresh(@RequestBody ReportListDto dto){
		log.info("listrefresh(dto = {})",dto);
		
		adminservice.update(dto);
		
		List<ReportReadDto> list;
		if(dto.getCheck() == 1) {
			
			list = adminservice.readlist();
			
		} else if(dto.getCheck() == 2) {
			
			list = adminservice.readchecklist();
			
		} else {
			
			list = adminservice.readendlist();
			
		}
		
		return ResponseEntity.ok(list);
		
	}
	
	
	
}
