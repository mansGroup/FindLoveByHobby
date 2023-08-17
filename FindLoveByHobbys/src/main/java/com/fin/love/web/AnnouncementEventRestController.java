package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fin.love.dto.announcementEvent.AnnouncementEventCreateDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.service.AnnouncementEventService;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/eventUpload")
@RequiredArgsConstructor

public class AnnouncementEventRestController {
	
	@Value("${com.example.upload.path}") // application.properties의 변수
	private String EventUploadPath;
	
	private final PictureService pictureService;
	private final AnnouncementEventService announcementEventService;
	
	@PostMapping("/usualImage/{id}")
	public ResponseEntity<String> imageUpload1(@RequestBody MultipartFile uploadFile, @PathVariable Long id) throws Exception {
		log.info("usualImage()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("uploadPath >>>>>>>>>>>>> " + EventUploadPath);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
		
		// 이미지 파일만 업로드 가능
		if (uploadFile.getContentType().startsWith("image") == false) {
			return ResponseEntity.ok("false");
		}

		// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 파일 이름만 저장
		String originalName = uploadFile.getOriginalFilename();
		
		log.info("originalName >>>> " + originalName);
		
		// 마지막 "\\"찾고 인덱스에 +1을 해서 \\ 다음 문자부터 읽는다.
		String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

		// 날짜 폴더 생성
		String folderPath = pictureService.makeFolder();
		log.info("folderPath >>> " + folderPath);
		
		// UUID
		String uuid = UUID.randomUUID().toString();

		// 저장할 파일 이름 중간에 "_"를 이용해 구분
		String saveName = EventUploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

		Path savePath = Paths.get(saveName);
		log.info("savePath >>>>>>>>>>> " + savePath);
		
		String strsavePath = String.valueOf(savePath);
		
		String saveImagePathName = folderPath.replace("\\", "/");
		saveImagePathName += "/";
		saveImagePathName += strsavePath.substring(strsavePath.lastIndexOf("\\") + 1);
		try {
			uploadFile.transferTo(savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		announcementEventService.pic1SaveImage(id, saveImagePathName, name);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/modifyImage/{id}")
	public ResponseEntity<String> imageUpload2(@RequestBody MultipartFile uploadFile, @PathVariable Long id) throws Exception {
		log.info("usualImage()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("uploadPath >>>>>>>>>>>>> " + EventUploadPath);
		
		// 이미지 파일만 업로드 가능
		if (uploadFile.getContentType().startsWith("image") == false) {
			return ResponseEntity.ok("false");
		}

		// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 파일 이름만 저장
		String originalName = uploadFile.getOriginalFilename();
		
		log.info("originalName >>>> " + originalName);
		
		// 마지막 "\\"찾고 인덱스에 +1을 해서 \\ 다음 문자부터 읽는다.
		String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

		// 날짜 폴더 생성
		String folderPath = pictureService.makeFolder();
		log.info("folderPath >>> " + folderPath);
		
		// UUID
		String uuid = UUID.randomUUID().toString();

		// 저장할 파일 이름 중간에 "_"를 이용해 구분
		String saveName = EventUploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

		Path savePath = Paths.get(saveName);
		log.info("savePath >>>>>>>>>>> " + savePath);
		
		String strsavePath = String.valueOf(savePath);
		
		String saveImagePathName = folderPath.replace("\\", "/");
		saveImagePathName += "/";
		saveImagePathName += strsavePath.substring(strsavePath.lastIndexOf("\\") + 1);
		try {
			uploadFile.transferTo(savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		announcementEventService.pic1SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	
	@PostMapping("/eventCreate")
	public ResponseEntity<Long> create(AnnouncementEventCreateDto dto) {
		log.info("create(dto = {})", dto);
		
		AnnouncementEvent ane = announcementEventService.create(dto);
		
		return ResponseEntity.ok(ane.getId());
	}
	
}
