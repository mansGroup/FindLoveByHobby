package com.fin.love.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.fin.love.dto.member.ManagerUserListDto;
import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.HobbyPictureRepository;
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.repository.profile.Academic;
import com.fin.love.repository.profile.AcademicRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Drings;
import com.fin.love.repository.profile.DringsRepository;
import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.HeightRepository;
import com.fin.love.repository.profile.Income;
import com.fin.love.repository.profile.IncomeRepository;
import com.fin.love.repository.profile.Jobs;
import com.fin.love.repository.profile.JobsRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.Religion;
import com.fin.love.repository.profile.ReligionRepository;
import com.fin.love.repository.profile.Smoker;
import com.fin.love.repository.profile.SmokerRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerUserListService {
	
	private final MemberRepository memberRepository;
	private final ProfileRepository profileRepository;
	private final AssessmentRepository assessmentRepository;
	private final PictureRepository pictureRepository;
	private final HobbyPictureRepository hobbyPictureRepository;
	private final AgeRepository ageRepository;
	private final AcademicRepository academicRepository;
	private final IncomeRepository incomeRepository;
	private final JobsRepository jobsRepository;
	private final ReligionRepository religionRepository;
	private final PictureService pictureService;
	private final HeightRepository heightRepository;
	private final SmokerRepository smokerRepository;
	private final DringsRepository dringsRepository;
	private final HobbyRepository hobbyRepository;
	private final UserHobbyRepository userHobbyRepository;
	
	public ManagerUserListDto dtoCreate(String userId) {
		log.info("dtoCreate(userId = {})", userId);

		Member member = memberRepository.findById(userId).orElseThrow();
		String gender = "";
		if (member.getSex() == 2) {
			gender = "여자";
		} else {
			gender = "남자";
		}
		
		Profile profile = profileRepository.findById(userId).orElseThrow();
		List<Age> ages = ageRepository.findAll();
		String age = ages.get(profile.getUserAge() - 1).getAgeName();
		List<Academic> academics = academicRepository.findAll();
		String academic = academics.get(profile.getUserAcademic() - 1).getAcademicName();
		List<Income> incomes = incomeRepository.findAll();
		String income = incomes.get(profile.getUserIncome() - 1).getIncome();
		List<Jobs> jobs = jobsRepository.findAll();
		String userJob = jobs.get(profile.getUserJob() - 1).getJobName();
		List<Religion> religions = religionRepository.findAll();
		String religion = religions.get(profile.getUserReligion() - 1).getReligionName();
		List<Drings> drings = dringsRepository.findAll();
		String drink = drings.get(profile.getUserDrinks() - 1).getDringsName();
		List<Height> heights = heightRepository.findAll();
		String height = heights.get(profile.getUserHeight() - 1).getHeightName();
		List<Smoker> smokers = smokerRepository.findAll();
		String smoker = smokers.get(profile.getUserSmoker() - 1).getSmokerName();
		
		Assessment assessment = assessmentRepository.findById(userId).orElse(null);
		int sexy = 0;
		int beautiful = 0;
		int cute = 0;
		int handsome = 0;
		int wonderful = 0;
		
		if (assessment != null) {
			sexy = assessment.getSexy();
			beautiful = assessment.getBeautiful();
			cute = assessment.getCute();
			handsome = assessment.getHandsome();
			wonderful = assessment.getWonderful();
		}
		
		Picture pic = pictureRepository.findById(userId).orElseThrow();
		HobbyPicture hobbyPic = hobbyPictureRepository.findById(userId).orElseThrow();
		String pic1 = pictureService.imageChange(pic.getPic1());
		String pic2 = pictureService.imageChange(pic.getPic2());
		String pic3 = pictureService.imageChange(pic.getPic3());
		String hobbyPic1 = pictureService.imageChange(hobbyPic.getHobbyPic1());
		String hobbyPic2 = pictureService.imageChange(hobbyPic.getHobbyPic2());
		String hobbyPic3 = pictureService.imageChange(hobbyPic.getHobbyPic3());
		
		List<UserHobby> userHobbys = userHobbyRepository.findByUserid(userId);
		
		String hobby1 = hobbyRepository.findById(userHobbys.get(0).getHobbyId()).orElse(null).getHobbyName();
		String hobby2 = "";
		String hobby3 = "";
		
		if (userHobbys.size() >= 2) {
			hobby2 = hobbyRepository.findById(userHobbys.get(1).getHobbyId()).orElse(null).getHobbyName();
		}
		
		if (userHobbys.size() >= 3) {
			hobby3 = hobbyRepository.findById(userHobbys.get(2).getHobbyId()).orElse(null).getHobbyName();
		}
		
		
		ManagerUserListDto dto = new ManagerUserListDto(
				userId,  // 아이디
				member.getName(),  // 이름
				member.getNickname(), // 닉네임
				member.getEmail(),  // 이메일
				gender,  // 성별
				member.getRole(),  // 권한
				member.getPhone(),  // 폰
				member.getAddress(),  // 주소
				member.getBirthday(),  // 생일
				member.getCreatedTime(),  // 가입 시간
				member.getModifiedTime(),  // 정보 수정 시간
				age, // 나이
				drink,  // 음주
				smoker,  // 흡연
				height,  // 키
				academic,  // 학교
				income,  // 연봉
				userJob,  // 직업
				religion,  // 종교
				profile.getUserIntroduce(),  // 소개글
				sexy,  // sexy 카운트
				beautiful, // Beautiful 카운트
				cute, // Cute 카운트
				handsome, // Handsome 카운트
				wonderful,// Wonderful 카운트
				hobby1, // 취미 1
				hobby2, // 취미 2
				hobby3, // 취미 3
				pic1,  // usual 사진1
				pic2,  // usual 사진2
				pic3,  // usual 사진3
				hobbyPic1,  // hobby 사진1
				hobbyPic2,  // hobby 사진2
				hobbyPic3); // hobby 사진3
		
		return dto;
	}
	
	
}
