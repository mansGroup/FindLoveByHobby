package com.fin.love.web;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.dto.profile.ProfileUpdateDto;
import com.fin.love.dto.profile.UserHobbyDto;
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
import com.fin.love.repository.profile.UserHobby;
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

		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> age = ageService.readAgeList();
		List<Height> height = heightService.readHeightList();

		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", age);
		model.addAttribute("heights", height);

		return "/profile/profiles";
	}

	
	// 클라이언트에서 받은 데이터를 DB로 넘겨줌
	@PostMapping("/user/profileimage")
	public String createProfile(ProfileCreateDto dto, @RequestParam(value = "hobbyId") String hobbyID) {
		log.info("createProfile(dto={}, hobbyID={})POST", dto, hobbyID);
		log.info("hobbyID >>>>>>>>>>>>>>>>>> {}", hobbyID);

		profileService.createProfile(dto);
		
		StringTokenizer st = new StringTokenizer(hobbyID, ",");
		Long hobbyId1 = Long.valueOf(st.nextToken());
		Long hobbyId2 = Long.valueOf(st.nextToken());
		Long hobbyId3 = Long.valueOf(st.nextToken());
		
		UserHobbyDto hobbyDto1 = new UserHobbyDto(dto.getUserId(), hobbyId1);
		UserHobbyDto hobbyDto2 = new UserHobbyDto(dto.getUserId(), hobbyId2);
		UserHobbyDto hobbyDto3 = new UserHobbyDto(dto.getUserId(), hobbyId3);
		
		hobbyService.hobbySave(hobbyDto1);
		log.info("hobbyDto= {}", hobbyDto1);
		
		hobbyService.hobbySave(hobbyDto2);
		log.info("hobbyDto= {}", hobbyDto2);
		
		hobbyService.hobbySave(hobbyDto3);
		log.info("hobbyDto= {}", hobbyDto3);
		
		return "/profile/profileimage";
	}

	
	// DB에 저장되어 있는 사용자 데이터를 수정페이지로 읽기
	@GetMapping({"/profilemodify", "/testmodifybutton"})
	public void profileModify(Model model) {
		String userId = "daehan";
		log.info("profileModify(userId={})", userId);

		Profile profile = profileService.profileModify(userId);
		log.info("profile >>> " + profile);
		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> age = ageService.readAgeList();
		List<Height> height = heightService.readHeightList();
		
		List<UserHobby> hobbys = hobbyService.findById(userId);		
		model.addAttribute("hobbys1", hobbys.get(0).getHobbyId());
		model.addAttribute("hobbys2", hobbys.get(1).getHobbyId());
		model.addAttribute("hobbys3", hobbys.get(2).getHobbyId());
		
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
		String userHeight = height.get(profile.getUserHeight() - 1).getHeightName(); 
		
		model.addAttribute("userHeight", userHeight);
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
	public String profileUpdate(ProfileUpdateDto dto, @RequestParam(value = "hobbyId") String hobbyID) {
		log.info("profileUpdate({})", dto);
		
		profileService.profileUpdate(dto);
		
		StringTokenizer st = new StringTokenizer(hobbyID, ",");
		Long hobbyId1 = Long.valueOf(st.nextToken());
		Long hobbyId2 = Long.valueOf(st.nextToken());
		Long hobbyId3 = Long.valueOf(st.nextToken());
		
		UserHobbyDto hobbyDto1 = new UserHobbyDto(dto.getUserId(), hobbyId1);
		UserHobbyDto hobbyDto2 = new UserHobbyDto(dto.getUserId(), hobbyId2);
		UserHobbyDto hobbyDto3 = new UserHobbyDto(dto.getUserId(), hobbyId3);
		
		hobbyService.hobbyByIdAllDelete(dto.getUserId());
		
		hobbyService.hobbySave(hobbyDto1);
		log.info("hobbyDto= {}", hobbyDto1);
		
		hobbyService.hobbySave(hobbyDto2);
		log.info("hobbyDto= {}", hobbyDto2);
		
		hobbyService.hobbySave(hobbyDto3);
		log.info("hobbyDto= {}", hobbyDto3);
		
		return "redirect:/mypage/" + dto.getUserId();
	}
	
	
}
