package com.fin.love.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.repository.image.Picture;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingListController {
	
	private final PictureService pictureService;
	
	@GetMapping("/matchingList/{userId}")
	public String listHome(@PathVariable String userId, Model model) throws Exception {
        log.info("listHome()");
        
        // 매칭 유저 뽑기
//        List<MatchingListDto> memberList = matchingService.matching(userId);
        
        // 자기 소개 테이블 컬럼 불러오고 나이 닉네임 호감도/카운트 사진
        
        Picture pic = pictureService.findById(userId);
        String usualPic1 = "/images/uploadImages/";
        usualPic1 += pic.getPic1();
        
        model.addAttribute("pic1", usualPic1);
        
        return "/matching/matchingList";
    }
	
	@GetMapping("/test")
	public void sssss() {
		
	}
	
}
