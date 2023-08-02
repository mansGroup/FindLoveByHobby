package com.fin.love.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureService {
	
	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;
	
	private final PictureRepository pictureRepository;
	
	public String makeFolder() {
		log.info("makeForder()");

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		String folderPath = str.replace("/", File.separator);

		// make folder ----
		File uploadPatheFolder = new File(uploadPath, folderPath);

		if (uploadPatheFolder.exists() == false) {
			uploadPatheFolder.mkdirs();
		}

		return folderPath;
	}
	
}
