package com.fin.love.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.fin.love.dto.member.ManagerUserListDto;
import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;
import com.fin.love.repository.image.HobbyPicture;
import com.fin.love.repository.image.HobbyPictureRepository;
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.repository.profile.Academic;
import com.fin.love.repository.profile.AcademicRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Income;
import com.fin.love.repository.profile.IncomeRepository;
import com.fin.love.repository.profile.Jobs;
import com.fin.love.repository.profile.JobsRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.Religion;
import com.fin.love.repository.profile.ReligionRepository;
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
	
	public ManagerUserListDto dtoCreate(String userId) {
		log.info("dtoCreate(userId = {})", userId);

		Member member = memberRepository.findById(userId).orElseThrow();
		
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
		
		Assessment assessment = assessmentRepository.findById(userId).orElseThrow();
		StringTokenizer st = new StringTokenizer(assessmentMaxValue(assessment), "/");
		String assessmentName = st.nextToken();
		int assessmentCount = Integer.parseInt(st.nextToken());
		
		Picture pic = pictureRepository.findById(userId).orElseThrow();
		HobbyPicture hobbyPic = hobbyPictureRepository.findById(userId).orElseThrow();
		String pic1 = pictureService.imageChange(pic.getPic1());
		String pic2 = pictureService.imageChange(pic.getPic2());
		String pic3 = pictureService.imageChange(pic.getPic3());
		String hobbyPic1 = pictureService.imageChange(hobbyPic.getHobbyPic1());
		String hobbyPic2 = pictureService.imageChange(hobbyPic.getHobbyPic2());
		String hobbyPic3 = pictureService.imageChange(hobbyPic.getHobbyPic3());
		
		ManagerUserListDto dto = new ManagerUserListDto(
				userId,  // 아이디
				member.getName(),  // 이름
				member.getNickname(), // 닉네임
				member.getEmail(),  // 이메일
				member.getSex(),  // 성별
				member.getRole(),  // 권한
				member.getPhone(),  // 폰
				member.getAddress(),  // 주소
				member.getBirthday(),  // 생일
				member.getCreatedTime(),  // 가입 시간
				member.getModifiedTime(),  // 정보 수정 시간
				age, // 나이
				profile.getUserDrinks(),  // 음주
				profile.getUserSmoker(),  // 흡연
				profile.getUserHeight(),  // 키
				academic,  // 학교
				income,  // 연봉
				userJob,  // 직업
				religion,  // 종교
				profile.getUserIntroduce(),  // 소개글
				assessmentName,  // 호감도
				assessmentCount,  // 호감도 카운트
				pic1,  // usual 사진1
				pic2,  // usual 사진2
				pic3,  // usual 사진3
				hobbyPic1,  // hobby 사진1
				hobbyPic2,  // hobby 사진2
				hobbyPic3); // hobby 사진3
		
		return dto;
	}
	
	// 호감도가 제일 높은 걸 찾기 위한 메서드
		private String assessmentMaxValue(Assessment assessment) {
			String str = "";
			int max = 0;
			
			if (assessment.getBeautiful() > assessment.getHandsome() && assessment.getBeautiful() > assessment.getSexy()
					&& assessment.getBeautiful() >= assessment.getWonderful() && assessment.getBeautiful() >= assessment.getCute()) {
				str = "Beautiful";
				max = assessment.getBeautiful();
			} else if (assessment.getCute() >= assessment.getBeautiful() && assessment.getCute() >= assessment.getHandsome()
					&& assessment.getCute() >= assessment.getSexy() && assessment.getCute() >= assessment.getWonderful()) {
				str = "Cute";
				max = assessment.getCute();
			} else if (assessment.getHandsome() > assessment.getBeautiful() && assessment.getHandsome() >= assessment.getCute()
					&& assessment.getHandsome() > assessment.getSexy() && assessment.getHandsome() >= assessment.getWonderful()) {
				str = "Handsome";
				max = assessment.getHandsome();
			} else if (assessment.getSexy() >= assessment.getBeautiful() && assessment.getSexy() >= assessment.getCute()
					&& assessment.getSexy() >= assessment.getHandsome() && assessment.getSexy() >= assessment.getWonderful()) {
				str = "Sexy";
				max = assessment.getSexy();
			} else if (assessment.getWonderful() >= assessment.getBeautiful() && assessment.getWonderful() >= assessment.getCute()
					&& assessment.getWonderful() >= assessment.getHandsome() && assessment.getWonderful() >= assessment.getSexy()) {
				str = "Wonderful";
				max = assessment.getWonderful();
			}
			
			if (max == 0) {
				return null;
			}
			
			str += "/";
			str += String.valueOf(max);
			
			return str;
		}
		
		
	
}
