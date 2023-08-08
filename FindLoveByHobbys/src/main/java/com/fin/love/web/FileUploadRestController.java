package com.fin.love.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<String> imageUpload1(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("imageUpload1()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.pic1SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/usualImage2/{id}")
	public ResponseEntity<String> imageUpload2(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("imageUpload2()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.pic2SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/usualImage3/{id}")
	public ResponseEntity<String> imageUpload3(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("imageUpload3()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.pic3SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/hobbyusualimage1/{id}")
	public ResponseEntity<String> hobbyImageUpload1(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("hobbyImageUpload1()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.hobbyPic1SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/hobbyusualimage2/{id}")
	public ResponseEntity<String> hobbyImageUpload2(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("hobbyImageUpload2()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.hobbyPic2SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/hobbyusualimage3/{id}")
	public ResponseEntity<String> hobbyImageUpload3(@RequestBody MultipartFile uploadFile, @PathVariable String id) throws Exception {
		log.info("hobbyImageUpload3()");
		log.info("file >>>>>>>>>>>>> " + uploadFile);
		log.info("User Id >>>>>>>>>>>>> " + id);
		log.info("uploadPath >>>>>>>>>>>>> " + uploadPath);
		
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
		String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

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
		
		pictureService.hobbyPic3SaveImage(id, saveImagePathName);
		
		return ResponseEntity.ok(saveImagePathName);
	}
	
	@PostMapping("/incoding/{id}")
	public String incodingImage(@PathVariable String id) throws Exception {
		log.info("incodingImage(id = {})", id);
		
		String pic = "C:";
		pic += pictureService.pic1Image(id);
		log.info("pic >>>> " + pic);
		
		String resultImage = pictureService.stringToIncoding(pic);
		log.info("resultImage >>>>>> " + resultImage);
		
		return resultImage;
	}
	
	@PostMapping("/removeFile") // 미완성
    public ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);

            boolean result = file.delete();

            File thumbnail = new File(file.getParent(),"s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result,HttpStatus.OK);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PostMapping("/usualPic1/delete/{userId}")
	public void usualPic1Delete(@PathVariable String userId) {
		log.info("usualPic1Delete(userId = {})", userId);
		
		pictureService.usualPic1Delete(userId);
	}
	
	@PostMapping("/usualPic2/delete/{userId}")
	public void usualPic2Delete(@PathVariable String userId) {
		log.info("usualPic2Delete(userId = {})", userId);
		
		pictureService.usualPic2Delete(userId);
	}
	
	@PostMapping("/usualPic3/delete/{userId}")
	public void usualPic3Delete(@PathVariable String userId) {
		log.info("usualPic3Delete(userId = {})", userId);
		
		pictureService.usualPic3Delete(userId);
	}
	
	@PostMapping("/hobbyPic1/delete/{userId}")
	public void hobbyPic1Delete(@PathVariable String userId) {
		log.info("hobbyPic1Delete(userId = {})", userId);
		
		pictureService.hobbyPic1Delete(userId);
	}
	
	@PostMapping("/hobbyPic2/delete/{userId}")
	public void hobbyPic2Delete(@PathVariable String userId) {
		log.info("hobbyPic2Delete(userId = {})", userId);
		
		pictureService.hobbyPic2Delete(userId);
	}
	
	@PostMapping("/hobbyPic3/delete/{userId}")
	public void hobbyPic3Delete(@PathVariable String userId) {
		log.info("hobbyPic3Delete(userId = {})", userId);
		
		pictureService.hobbyPic3Delete(userId);
	}
	
}
