package com.fin.love.web;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.dto.profile.ProfileSearchDto;
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
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.service.MemberService;
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
	private final MemberService memberService;
	private final HobbyService hobbyService;
	private final AgeService ageService;
	private final HeightService heightService;
	private final AcademicService academicService;
	private final JobService jobService;
	private final IncomeService incomeService;
	private final ReligionService religionService;
	private final DringsService dringsService;
	private final SmokerService smokerService;
	private final MemberRepository memberRepository;

	// 취미, 나이, 키 리스트 불러서 뷰에 출력
	@GetMapping("/profiles")
	public String profileHome(Model model) {
		log.info("profileHome()");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String userId = authentication.getName();
		
		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> age = ageService.readAgeList();
		List<Height> height = heightService.readHeightList();
		List<Member> members = memberService.readMembefrList();
		Member member = memberRepository.findById(userId).orElseThrow();
		
		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", age);
		model.addAttribute("heights", height);
		model.addAttribute("members", members);
		model.addAttribute("member", member);

		return "/profile/profiles";
	}

	@GetMapping("/profilesearch")
	public String search(Model model, @RequestParam String hobby1, @RequestParam String hobby2, @RequestParam String hobby3,
			@RequestParam int age, @RequestParam int height) {
		log.info("search()");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String userId = authentication.getName();

		// 조건 검색할 리스트 불러옴
		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> ages = ageService.readAgeList();
		List<Height> heights = heightService.readHeightList();

		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", ages);
		model.addAttribute("heights", heights);
		
		List<Member> members = new ArrayList<>();
		
		if (age == 0 && height == 0 && hobby1.equals("0") && hobby2.equals("0") && hobby3.equals("0")) {
			model.addAttribute("members", members);
			model.addAttribute("size", 0);
			return "/profile/profilesearch";
		}
		
		// 조건 검색
		// 취미 선택 찾기

		Long hobbyId1 = null;
		Long hobbyId2 = null;
		Long hobbyId3 = null;
		
		if (!hobby1.equals("-2")) {
			hobbyId1 = Long.parseLong(hobby1);
		}
		
		if (!hobby2.equals("-2")) {
			hobbyId2 = Long.parseLong(hobby2);
		}
		
		if (!hobby3.equals("-2")) {
			hobbyId3 = Long.parseLong(hobby3);
		}
		
		
		// 취미를 가진 사람들 찾기
		members = profileService.findByhobbys(hobbyId1, hobbyId2, hobbyId3, userId);
		
		// 선택한 나이 +-5살 까지 사람들 찾기
		List<Member> membersByAge = profileService.membersByage(members, age);
		
		// 선택한 키의 +-5cm 까지 사람들 찾기
		List<Member> membersByHeight = profileService.membersByHeight(members, height);
		
		if (membersByHeight == null) {
			List<ProfileSearchDto> dto = profileService.toProfileSearchDto(membersByAge);
			dto = profileService.dtoSort(dto, userId);
			log.info("dto 1 >>>> " + dto);
			
			model.addAttribute("member1", dto.get(0));
			model.addAttribute("member2", dto.get(1));
			model.addAttribute("member3", dto.get(2));
			model.addAttribute("member4", dto.get(3));
			model.addAttribute("size", dto.size());
			
		} else if (membersByAge == null) {
			List<ProfileSearchDto> dto = profileService.toProfileSearchDto(members);
			dto = profileService.dtoSort(dto, userId);
			log.info("dto 2 >>>> " + dto);
			model.addAttribute("member1", dto.get(0));
			model.addAttribute("member2", dto.get(1));
			model.addAttribute("member3", dto.get(2));
			model.addAttribute("member4", dto.get(3));
			model.addAttribute("size", dto.size());
		} else {
			List<ProfileSearchDto> dto = profileService.toProfileSearchDto(membersByHeight);
			dto = profileService.dtoSort(dto, userId);
			log.info("dto 3 >>>> " + dto);
			model.addAttribute("member1", dto.get(0));
			model.addAttribute("member2", dto.get(1));
			model.addAttribute("member3", dto.get(2));
			model.addAttribute("member4", dto.get(3));
			model.addAttribute("size", dto.size());
		}
		
		
		return "/profile/profilesearch";
	}

	// 클라이언트에서 받은 데이터를 DB로 넘겨줌
	@PostMapping("/user/profileimage")
	public String createProfile(ProfileCreateDto dto, @RequestParam(value = "hobbyId") String hobbyID) {
		log.info("createProfile(dto={}, hobbyID={})POST", dto, hobbyID);
		log.info("hobbyID >>>>>>>>>>>>>>>>>> {}", hobbyID);

		profileService.createProfile(dto);

		StringTokenizer st = new StringTokenizer(hobbyID, ",");


		Long hobbyId1 = null;
		Long hobbyId2 = null;
		Long hobbyId3 = null;

		if (st.countTokens() == 1) {
			hobbyId1 = Long.valueOf(st.nextToken());
		} else if (st.countTokens() == 2) {
			hobbyId1 = Long.valueOf(st.nextToken());
			hobbyId2 = Long.valueOf(st.nextToken());
		} else {
			hobbyId1 = Long.valueOf(st.nextToken());
			hobbyId2 = Long.valueOf(st.nextToken());
			hobbyId3 = Long.valueOf(st.nextToken());
		}

		UserHobbyDto hobbyDto1 = null;
		UserHobbyDto hobbyDto2 = null;
		UserHobbyDto hobbyDto3 = null;

		if (hobbyId1 != null) {
			hobbyDto1 = new UserHobbyDto(dto.getUserId(), hobbyId1);
			hobbyService.hobbySave(hobbyDto1);
			log.info("hobbyDto= {}", hobbyDto1);
		}
		if (hobbyId2 != null) {
			hobbyDto2 = new UserHobbyDto(dto.getUserId(), hobbyId2);
			hobbyService.hobbySave(hobbyDto2);
			log.info("hobbyDto= {}", hobbyDto2);
		}
		if (hobbyId3 != null) {
			hobbyDto3 = new UserHobbyDto(dto.getUserId(), hobbyId3);
			hobbyService.hobbySave(hobbyDto3);
			log.info("hobbyDto= {}", hobbyDto3);
		}


		return "redirect:/profile/profileimage";
	}

	// DB에 저장되어 있는 사용자 데이터를 수정페이지로 읽기
	@GetMapping("/profilemodify")
	public void profileModify(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = authentication.getName();
		log.info("profileModify(userId={})", userid);

		Profile profile = profileService.profileModify(userid);
		log.info("profile >>> " + profile);
		List<Hobby> hobby = hobbyService.readHobbyList();
		List<Age> age = ageService.readAgeList();
		List<Height> height = heightService.readHeightList();

		List<UserHobby> hobbys = hobbyService.findById(userid);
		
		String hobbys1name =  hobbyService.findHobbyName(hobbys.get(0).getHobbyId());
		String hobbys2name =  hobbyService.findHobbyName(hobbys.get(1).getHobbyId());
		String hobbys3name =  hobbyService.findHobbyName(hobbys.get(2).getHobbyId());
		
		if (hobbys.size() == 1) {
			model.addAttribute("hobbys1", hobbys.get(0).getHobbyId());
			model.addAttribute("hobbys1name", hobbys1name);
		} else if (hobbys.size() == 2) {
			model.addAttribute("hobbys1", hobbys.get(0).getHobbyId());
			model.addAttribute("hobbys1name", hobbys1name);
			model.addAttribute("hobbys2", hobbys.get(1).getHobbyId());
			model.addAttribute("hobbys2name", hobbys2name);
		} else {
			model.addAttribute("hobbys1", hobbys.get(0).getHobbyId());
			model.addAttribute("hobbys1name", hobbys1name);
			model.addAttribute("hobbys2", hobbys.get(1).getHobbyId());
			model.addAttribute("hobbys2name", hobbys2name);
			model.addAttribute("hobbys3", hobbys.get(2).getHobbyId());
			model.addAttribute("hobbys3name", hobbys3name);
		}

		model.addAttribute("profile", profile);
		model.addAttribute("hobbys", hobby);
		model.addAttribute("ages", age);
		model.addAttribute("heights", height);

		List<Academic> academic = academicService.readAcademicList();
		List<Jobs> jobs = jobService.readJobsList();
		List<Income> income = incomeService.readIncomeList();
		List<Religion> religion = religionService.readReligionList();
		List<Drings> drings = dringsService.readDringsList();
		List<Smoker> smoker = smokerService.readSmokerList();

		String userAge = age.get(profile.getUserAge() - 1).getAgeName();
		String userAcademic = academic.get(profile.getUserAcademic() - 1).getAcademicName();
		String userJob = jobs.get(profile.getUserJob() - 1).getJobName();
		String userIncome = income.get(profile.getUserIncome() - 1).getIncome();
		String userReligion = religion.get(profile.getUserReligion() - 1).getReligionName();
		String userDrings = drings.get(profile.getUserDrinks() - 1).getDringsName();
		String userSmoker = smoker.get(profile.getUserSmoker() - 1).getSmokerName();
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
	public String profileUpdate(ProfileUpdateDto dto, 
			@RequestParam String hobby1, 
			@RequestParam String hobby2, 
			@RequestParam String hobby3) {
		log.info("profileUpdate({})", dto);
		log.info("hobby1" + hobby1);
		log.info("hobby2" + hobby2);
		log.info("hobby3" + hobby3);

		profileService.profileUpdate(dto);

		Long hobbyId1 = null;
		Long hobbyId2 = null;
		Long hobbyId3 = null;
		
		if (!hobby1.equals("-2")) {
			hobbyId1 = Long.parseLong(hobby1);
		}
		
		if (!hobby2.equals("-2")) {
			hobbyId2 = Long.parseLong(hobby2);
		}
		
		if (!hobby3.equals("-2")) {
			hobbyId3 = Long.parseLong(hobby3);
		}

		UserHobbyDto hobbyDto1 = null;
		UserHobbyDto hobbyDto2 = null;
		UserHobbyDto hobbyDto3 = null;

		if (hobbyId1 != null) {
			hobbyDto1 = new UserHobbyDto(dto.getUserId(), hobbyId1);
			hobbyService.hobbySave(hobbyDto1);
			log.info("hobbyDto= {}", hobbyDto1);
		}
		if (hobbyId2 != null) {
			hobbyDto2 = new UserHobbyDto(dto.getUserId(), hobbyId2);
			hobbyService.hobbySave(hobbyDto2);
			log.info("hobbyDto= {}", hobbyDto2);
		}
		if (hobbyId3 != null) {
			hobbyDto3 = new UserHobbyDto(dto.getUserId(), hobbyId3);
			hobbyService.hobbySave(hobbyDto3);
			log.info("hobbyDto= {}", hobbyDto3);
		}

		return "redirect:/mypage/room";
	}

}
