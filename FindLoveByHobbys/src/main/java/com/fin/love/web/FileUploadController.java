package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.Picture;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class FileUploadController {
	
	private final PictureService pictureService;
	
	@GetMapping("/imageTest")
	public void testPage() {
		
	}
	
	@GetMapping("/profileimage")
	public String image(Model model) {
		String userId = "a"; // 유저 아이디 나중에 받아오기
		log.info("imageUploadPage(userId = {})", userId);
		
		// 데이터가 있는지 확인
		int picCheck = pictureService.checkUserId(userId);
		int bhobbyPicCheck = pictureService.hobbyCheckUserId(userId);
		
		if (picCheck == 1) {
			// 데이터가 없으면 페이지에 들어오면 데이터 생성
			log.info("일반테이블 생성.");
			pictureService.newMemberPicture(userId);
		}
		
		if (bhobbyPicCheck == 1) {
			// 데이터가 없으면 페이지에 들어오면 데이터 생성
			log.info("취미테이블 생성.");
			pictureService.newMemberHobbyPicture(userId);
		}
		
		String baseImage = "/images/Adding_a_Person_Image.png";
		Picture pic = pictureService.baseUploadImage(userId);
		HobbyPicture hobbyPic = pictureService.hobbyBaseUploadImage(userId);
		
		if (pic.getPic1() == null) {
			pictureService.pic1SaveImage(userId, baseImage);
		}
		
		if (pic.getPic2() == null) {
			pictureService.pic2SaveImage(userId, baseImage);
		}
		
		if (pic.getPic3() == null) {
			pictureService.pic3SaveImage(userId, baseImage);
		}
		
		if (hobbyPic.getHobbyPic1() == null) {
			pictureService.hobbyPic1SaveImage(userId, baseImage);
		}
		
		if (hobbyPic.getHobbyPic2() == null) {
			pictureService.hobbyPic2SaveImage(userId, baseImage);
		}
		
		if (hobbyPic.getHobbyPic3() == null) {
			pictureService.hobbyPic3SaveImage(userId, baseImage);
		}
		
		model.addAttribute("userPic", pic);
		model.addAttribute("userHobbypic", hobbyPic);
		
		return "/profile/image_upload";
	}
	
	
	
}
