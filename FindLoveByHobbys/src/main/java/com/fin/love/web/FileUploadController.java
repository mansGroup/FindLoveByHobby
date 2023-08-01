package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/upload")
public class FileUploadController {
	
	@Value("${com.example.upload.path}") // application.properties의 변수
    private String uploadPath;
	
	@PostMapping("/upload")
	public void imageUpload(MultipartFile[] uploadFiles) {
		log.info("imageUpload()");
		
		for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false){
                return;
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 파일 이름만 저장
            String originalName = uploadFile.getOriginalFilename();
            
            // 마지막 "\\"찾고 인덱스에 +1을 해서 \\ 다음 문자부터 읽는다.
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "_"를 이용해 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
		
		
	}
	
	private String makeFolder() {
		log.info("makeForder()");
		
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        String folderPath = str.replace("/", File.separator);
        
        // make folder ----
        File uploadPatheFolder = new File(uploadPath,folderPath);
        
        if(uploadPatheFolder.exists() == false){
            uploadPatheFolder.mkdirs();
        }
        
        return folderPath;
    }
	
	
}
