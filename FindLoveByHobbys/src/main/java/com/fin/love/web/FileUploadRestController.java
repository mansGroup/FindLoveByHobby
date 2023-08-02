package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fin.love.dto.image.FileUploadDto;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadRestController {

	@Value("${com.example.upload.path}") // application.properties의 변수
	private String uploadPath;
	
	private final PictureService pictureService;

	@PostMapping("/usualImage1/{id}")
	public void imageUpload(@RequestBody MultipartFile uploadFile, @PathVariable String id) {
		log.info("imageUpload()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		
		// 이미지 파일만 업로드 가능
		if (uploadFile.getContentType().startsWith("image") == false) {
			return;
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

		Path savePath = Paths.get(saveName);
		log.info("savePath >>>>>>>>>>> " + savePath);
		
		try {
			uploadFile.transferTo(savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}