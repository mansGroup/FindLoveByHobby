package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.facechat.ReportReadDto;
import com.fin.love.repository.facechat.Speakchat;
import com.fin.love.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class adminReportContorller {

	@Autowired
	private AdminService adminservice;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/reportlist")
	public String reportlist(Model model) {

		log.info("reportlist()");
		model.addAttribute("check", 1);
		List<ReportReadDto> list = adminservice.readlist();

		model.addAttribute("list", list);
		return "/admin/reportadmin";

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/reportcheck")
	public String reportcheck(Model model) {

		log.info("reportcheck()");
		model.addAttribute("check", 2);
		List<ReportReadDto> list = adminservice.readchecklist();

		model.addAttribute("list", list);
		return "/admin/reportadmin";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/reportend")
	public String reportend(Model model) {

		log.info("reportend()");
		model.addAttribute("check", 3);
		List<ReportReadDto> list = adminservice.readendlist();

		model.addAttribute("list", list);
		return "/admin/reportadmin";

	}

}
