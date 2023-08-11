package com.fin.love.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.matching.MatchingListDto;
import com.fin.love.repository.image.Picture;
import com.fin.love.service.MatchingService;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingListController {

	private final PictureService pictureService;
	private final MatchingService matchingService;

	@GetMapping("/matchingList/{userId}")
	public String listHome(@PathVariable String userId, Model model) throws Exception {
		log.info("listHome()");

		// 매칭 유저 뽑기
		List<MatchingListDto> memberList = matchingService.matching(userId);
		
		if (memberList == null) {
			return "redirect:/matching/matching/none";
		}
		
		// 매칭 유저에서 2명 선정하기
        List<MatchingListDto> bestMember = matchingService.matchingBest(memberList);
        
        MatchingListDto member1 = bestMember.get(0);
        MatchingListDto member2 = bestMember.get(1);
        
        Picture member1Pic = pictureService.findById(member1.getUserId());
        String member1UsualPic = matchingService.imageChange(member1Pic.getPic1());
        
        Picture member2Pic = pictureService.findById(member2.getUserId());
        String member2UsualPic = matchingService.imageChange(member2Pic.getPic1());

//		// 임시 값
//		Picture member1Pic = pictureService.findById("member1");
//		String member1UsualPic = matchingService.imageChange(member1Pic.getPic1());
//
//		Picture member2Pic = pictureService.findById("member2");
//		String member2UsualPic = matchingService.imageChange(member2Pic.getPic1());
        
		model.addAttribute("member1", member1); // member1
		model.addAttribute("member2", member2); // member2
		model.addAttribute("member1Pic", member1UsualPic); // member1 사진
		model.addAttribute("member2Pic", member2UsualPic); // member2 사진

		return "/matching/matchingList";
	}

	@GetMapping("/test")
	public void sssss() {

	}
	
	@GetMapping("/matching/none")
	public String matchingNone() {
		log.info("matchingNone()");
		
		return "/matching/matching_list_none";
	}
	
	
}
