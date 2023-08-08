package com.fin.love.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.dto.matching.MatchingListDto;
import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.HobbyPictureRepository;
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.profile.Profile;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MatchingDetailService;
import com.fin.love.service.MatchingService;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/matching")
public class MatchingDetailController {

	private final MatchingDetailService matchingDetailService;
	private final PictureService pictureService;
	private final MatchingService matchingService;

	// 사용자의 매칭 상세 정보 페이지로 이동하는 메서드
	@GetMapping("/matchingDetail/{id}")
	public String matchingDetail(@PathVariable("id") String id, Model model) {

		// 프로필 사진
		Picture member1Pic = pictureService.findById(id);

		String member1UsualPic1 = matchingService.imageChange(member1Pic.getPic1());
		String member1UsualPic2 = matchingService.imageChange(member1Pic.getPic2());
		String member1UsualPic3 = matchingService.imageChange(member1Pic.getPic3());

		model.addAttribute("usualPic1", member1UsualPic1);
		model.addAttribute("usualPic2", member1UsualPic2);
		model.addAttribute("usualPic3", member1UsualPic3);

		// 취미 사진
		HobbyPicture member1HobbyPic = pictureService.hobbyFindById(id);

		String member1HobbyPic1 = matchingService.imageChange(member1HobbyPic.getHobbyPic1());
		String member1HobbyPic2 = matchingService.imageChange(member1HobbyPic.getHobbyPic2());
		String member1HobbyPic3 = matchingService.imageChange(member1HobbyPic.getHobbyPic3());

		model.addAttribute("hobbyPic1", member1HobbyPic1);
		model.addAttribute("hobbyPic2", member1HobbyPic2);
		model.addAttribute("hobbyPic3", member1HobbyPic3);

//===============================이지미 관련 코드==========================================

		// 취미 목록 가져오기
		List<Hobby> hobbies = matchingDetailService.getAllHobbies();
		log.info("hobbies = {}", hobbies);
		model.addAttribute("hobbies", hobbies);

		// id에 해당하는 Assessment 가져오기
		Assessment assessment = matchingDetailService.getUserAssessment(id);
		log.info("assanswer = {}", assessment);
		model.addAttribute("assessment", assessment);

		// id에 해당하는 Profile 가져오기
		Profile profile = matchingDetailService.getUserProfile(id);
		log.info("proanswer = {}", profile);
		model.addAttribute("profile", profile);

		// 프로필 테이블의 직업의 번호를 조회하여 jobs테이블의 job이름을 가져옴
		String userJob = matchingDetailService.getUserJobName(profile.getUserJob());
		log.info("userJob = {}", userJob);
		model.addAttribute("job", userJob);

		// 프로필 테이블의 종교 번호를 조회하여 종교 테이블에서 종교 이름 가져옴
		String userReligion = matchingDetailService.getUserReligionName(profile.getUserReligion());
		log.info("userReligion = {}", userReligion);
		model.addAttribute("religion", userReligion);

		// 프로필 테이블에서 학력 번호를 조회하여 학력 데이블의 학력 이름을 가져옴.
		String userAcademic = matchingDetailService.getUserAcademicName(profile.getUserAcademic());
		log.info("userAcademic = {}", userAcademic);
		model.addAttribute("academic", userAcademic);

//		//프로필 테이블에서 키 번호를 조회하여 키 데이블의 키 이름을 가져옴.
//		String userHeight = matchingDetailService.getUserHeightName(profile.getUserHeight());
//		log.info("userHeight = {}", userHeight);
//		model.addAttribute("height", userHeight);

		// 프로필 테이블에서 나이 번호를 조회하여 나이 데이블의 나이 이름을 가져옴.
		String userAge = matchingDetailService.getUserAgeName(profile.getUserAge());
		log.info("userAge = {}", userAge);
		model.addAttribute("age", userAge);

//		// 프로필 테이블에서 음주 번호를 조회하여 음주 테이블에사 음주 이름을 가져옴.
//		String userDrinks = matchingDetailService.getUserDrinksName(profile.getUserDrinks());
//		log.info("userDrinks = {}", userDrinks);
//		model.addAttribute("drinks", userDrinks);
//
//		// 프로필 테이블에서 흡연 번호를 조회하여 흡연 테이블에사 흡연 이름을 가져옴.
//		String userSmoke = matchingDetailService.getUserSmokeName(profile.getUserSmoker());
//		log.info("userSmoke = {}", userSmoke);
//		model.addAttribute("smoke", userSmoke);

		// id에 해당하는 UserHobbies 가져오기
		List<String> userHobbies = matchingDetailService.getUserHobbies(id);
		model.addAttribute("userHobbies", userHobbies);
		log.info("userHobbies = {}", userHobbies);

		// id에 해당하는 Member 정보 가져오기
		Member member = matchingDetailService.getUserinfo(id);
		model.addAttribute("member", member);
		log.info("memanswer = {}", member);

		model.addAttribute("id", id);

		// 매칭 상세 정보 페이지로 이동
		return "/matching/matchingDetail";
	}

	// 테스트 페이지로 이동하는 메서드
	@GetMapping("/detailTest")
	public void detailTest(Model model) {
		// DB에 있는 Id를 넣어서 임시로 Id값을 넘겨줌.
		String code = "a";
		model.addAttribute("id", code);
	}

}
