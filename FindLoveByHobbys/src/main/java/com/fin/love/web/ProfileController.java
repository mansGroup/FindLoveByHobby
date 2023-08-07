package com.fin.love.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fin.love.profile.dto.ProfileCreateDto;
import com.fin.love.profile.dto.ProfileUpdateDto;
import com.fin.love.profile.dto.UserHobbyDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.profile.Academic;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.Drings;
import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.Income;
import com.fin.love.repository.profile.Jobs;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.Religion;
import com.fin.love.repository.profile.Smoker;
import com.fin.love.service.profile.AcademicService;
import com.fin.love.service.profile.AgeService;
import com.fin.love.service.profile.DringsService;
import com.fin.love.service.profile.HeightService;
import com.fin.love.service.profile.HobbyService;
import com.fin.love.service.profile.IncomeService;
import com.fin.love.service.profile.JobService;
import com.fin.love.service.profile.ProfileService;
import com.fin.love.service.profile.ReligionService;
import com.fin.love.service.profile.SmokerService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private HttpSession session;

	private final ProfileService profileService;
	private final HobbyService hobbyService;
	private final AgeService ageService;
	private final HeightService heightService;
	private final AcademicService academicService;
	private final JobService jobService;
	private final IncomeService incomeService;
	private final ReligionService religionService;
	private final DringsService dringsService;
	private final SmokerService smokerService;

	
	// 취미, 나이, 키 리스트 불러서 뷰에 출력
	@GetMapping("/profiles")
	public String profileHome(Model model) {
		log.info("profileHome()");

//		String name = (String) session.getAttribute("name");
//		log.info(name);
//		ProfileReadUserInfoDto dto = profileService.readMemberInfo(name);

		List<Hobby> hobby = hobbyService.readHobbyList();

		List<Age> age = ageService.readAgeList();

		List<Height> height = heightService.readHeightList();

		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", age);
		model.addAttribute("heights", height);

//		model.addAttribute("names", dto);
//		log.info("readMemberInfoSuccess!!!()");

		return "/profile/profiles";
	}

	
	// 클라이언트에서 받은 데이터를 DB로 넘겨줌
	@PostMapping("/user/profileimage")
	public String createProfile(ProfileCreateDto dto, UserHobbyDto hobbyDto, Model model) {
		log.info("createProfile(dto={})POST", dto, hobbyDto);

		profileService.createProfile(dto, hobbyDto);

		return "/profile/profileimage";
	}

	
	// DB에 저장되어 있는 사용자 데이터를 수정페이지로 읽기
	@GetMapping({"/profilemodify", "/testmodifybutton"})
	public void profileModify(Model model, String userId) {
		log.info("profileModify(userId={})", userId);

		userId = "11111";

		Profile profile = profileService.profileModify(userId);		
		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> age = ageService.readAgeList();
		List<Height> height = heightService.readHeightList();
				
		model.addAttribute("profile", profile);
		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", age);
		model.addAttribute("heights", height);
		
		List<Academic> academic =  academicService.readAcademicList();
		List<Jobs> jobs = jobService.readJobsList();
		List<Income> income = incomeService.readIncomeList();
		List<Religion> religion = religionService.readReligionList();
		List<Drings> drings = dringsService.readDringsList();
		List<Smoker> smoker = smokerService.readSmokerList();
		
		String userAge =  age.get(profile.getUserAge() -1).getAgeName();
		String userAcademic = academic.get(profile.getUserAcademic() -1).getAcademicName();
		String userJob = jobs.get(profile.getUserJob() -1).getJobName();
		String userIncome = income.get(profile.getUserIncome() -1).getIncome();
		String userReligion = religion.get(profile.getUserReligion() -1).getReligionName();
		String userDrings = drings.get(profile.getUserDrinks() -1).getDringsName();
		String userSmoker = smoker.get(profile.getUserSmoker() -1).getSmokerName();
		
		model.addAttribute("userAge", userAge);
		model.addAttribute("userAcademic", userAcademic);
		model.addAttribute("userJob", userJob);
		model.addAttribute("userIncome", userIncome);
		model.addAttribute("userReligion", userReligion);
		model.addAttribute("userDrings", userDrings);
		model.addAttribute("userSmoker", userSmoker);
	}
	
	
	// 프로필 수정 후 업데이트
	@PostMapping("/update")
	public String profileUpdate(ProfileUpdateDto dto) {
		log.info("profileUpdate({})", dto);
		
		profileService.profileUpdate(dto);
		
		return "redirect:/profile/profiles?id=" + dto.getUserId();
	}
	
	
	// 프로필 삭제
	@PostMapping("/delete")
	public String profileDelete(String userId) {
		log.info("profileDelete(userId={})", userId);
		
		profileService.profileDelete(userId);
		
		return "redirect:/profile/peofiles";
	}
	
}
