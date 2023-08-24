package com.fin.love.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;
import com.fin.love.repository.assessment.UserAssessment;
import com.fin.love.repository.assessment.UserAssessmentRepository;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.profile.Academic;
import com.fin.love.repository.profile.AcademicRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Drings;
import com.fin.love.repository.profile.DringsRepository;
import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.HeightRepository;
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
@RequiredArgsConstructor
@Service
public class MatchingDetailService {

	// 생성자를 사용한 의존성 주입:
	private final AssessmentRepository assessmentRepository;
	private final ProfileRepository profileRepository;
	private final HobbyRepository hobbyRepository;
	private final UserHobbyRepository userHobbyRepository;
	private final MemberRepository memberRepository;
	private final UserAssessmentRepository userAssessmentRepository;
	private final JobsRepository jobsRepository;
	private final ReligionRepository religionRepository;
	private final AcademicRepository academicRepository;
	private final DringsRepository dringsRepository;
	private final SmokerRepository smokerRepository;
	private final HeightRepository heightRepository;
	private final AgeRepository ageRepository;

	// DB 테이블에서 id 불러오기
	@Transactional(readOnly = true)
	public Assessment getUserAssessment(String id) {
		log.info("read(id={})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();
		return assessment; // 기본 객체 반환
	}

	// 취미를 가져오기위해
	public List<Hobby> getAllHobbies() {
		return hobbyRepository.findAll();
	}

	// 유저의 profile 정보를 가져오기 위해
	public Profile getUserProfile(String id) {
		return profileRepository.findById(id).orElse(new Profile()); // 기본 객체 반환
	}

	// 유저의 직업을 가져오기 위해
	public List<Jobs> getUserJob() {
		return jobsRepository.findAll();
	}

	// 유저의 직업 이름을 가져오기 위해
	public String getUserJobName(int jobNum) {
		List<Jobs> jo = getUserJob();

		return jo.get(jobNum - 1).getJobName();
	}

	// 유저의 종교를 가져오기 위해
	public List<Religion> getUserReligion() {
		return religionRepository.findAll();
	}

	// 유저의 종교 이름을 가져오기 위해
	public String getUserReligionName(int religingNum) {
		List<Religion> re = getUserReligion();

		return re.get(religingNum - 1).getReligionName();
	}

	// 유저의 학력 가져오기 위해
	public List<Academic> getUserAcdemic() {
		return academicRepository.findAll();
	}

	// 유저의 학력 이름을 가져오기 위해
	public String getUserAcademicName(int academicNum) {
		List<Academic> ac = getUserAcdemic();

		return ac.get(academicNum - 1).getAcademicName();
	}

	// 유저의 키 가져오기 위해
	public List<Height> getUserHeight() {
		return heightRepository.findAll();
	}

	// 유저의 키 이름을 가져오기 위해
	public String getUserHeightName(int heightNum) {
		List<Height> he = getUserHeight();

		return he.get(heightNum - 1).getHeightName();
	}
	

	// 유저의 나이 가져오기 위해
	public List<Age> getUserAge() {
		return ageRepository.findAll();
	}

	// 유저의 나이 이름을 가져오기 위해
	public String getUserAgeName(int ageNum) {
		List<Age> age = getUserAge();

		return age.get(ageNum - 1).getAgeName();
	}

	// 유저의 음주 여부를 가져오기 위해
	public List<Drings> getUserDrings() {
		return dringsRepository.findAll();
	}

	// 유저의 음주 이름을 가져오기 위해서
	public String getUserDrinksName(int drinksNum) {
		List<Drings> dr = getUserDrings();

		return dr.get(drinksNum - 1).getDringsName();
	}

	// 유저의 흡연 여부를 가져오기 위해
	public List<Smoker> getUserSmokers() {
		return smokerRepository.findAll();
	}

	// 유저의 흡연 이름을 가져오기 위해서
	public String getUserSmokerName(int smokerNum) {
		List<Smoker> sm = getUserSmokers();

		return sm.get(smokerNum - 1).getSmokerName();
	}

	// UserHobby와 Hobby에 Id를 비교 후 같다면 취미 목록을 출력한다.
	public List<String> getUserHobbies(String id) {
		List<UserHobby> list1 = userHobbyRepository.findByUserid(id);
		List<Hobby> list2 = getAllHobbies();
		List<String> hobbylist = new ArrayList<>();

		for (UserHobby x : list1) {

			for (Hobby y : list2) {

				if (x.getHobbyId() == y.getHobbyId()) {

					hobbylist.add(y.getHobbyName());
					break;
				}

			}

		}

		return hobbylist;

	}

	// 유저의 회원가입 정보를 가져오기 위해
	public Member getUserinfo(String id) {
		log.info("id = {}", id);

		return memberRepository.findById(id).orElseThrow();
	}

	public Assessment findById(String getterId) {
		log.info("findById(getterId = {})", getterId);

		return assessmentRepository.findById(getterId).orElseThrow();
	}

//======================== 호감을 보내기 위해============================================================

	@Transactional
	// @Transactional: 읽고 바로 실행시켜준다.
	public void sexyCountUp(String id) {
		log.info("sexyCountUp(id = {})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();

		int count = assessment.getSexy() + 1;

		assessment.sexyUpdate(count);
	}

	@Transactional
	public void beautifulCountUp(String id) {
		log.info("beautifulCountUp(id = {})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();

		int count = assessment.getBeautiful() + 1;

		assessment.beautifulUpdate(count);
	}

	@Transactional
	public void cuteCountUp(String id) {
		log.info("cuteCountUp(id = {})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();

		int count = assessment.getCute() + 1;

		assessment.cuteUpdate(count);
	}

	@Transactional
	public void wonderfulCoubtUp(String id) {
		log.info("wonderfulCountUp(id = {})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();

		int count = assessment.getWonderful() + 1;

		assessment.wonderfulUpdate(count);
	}

	@Transactional
	public void handsomeCountUp(String id) {
		log.info("handsomeCountUp(id={})", id);

		Assessment assessment = assessmentRepository.findById(id).orElseThrow();

		int count = assessment.getHandsome() + 1;

		assessment.handsomeUpdate(count);
	}
//======================== 호감을 보내기 위해============================================================

	// 호감 보낼 때 중복체크 하기위해
	public int assessment(String senderId, String getterId) {
		int result = 0;

		UserAssessment UA = userAssessmentRepository.findBySenderAndGetter(senderId, getterId);

		if (UA != null) {
			// 이미 호감을 보낸 기록이 있는 경우, 중복으로 처리
			result = 1;
		} else {
			// 호감을 보낸 기록이 없는 경우에만 저장
			UserAssessment entity = UserAssessment.builder().sender(senderId).getter(getterId).build();
			userAssessmentRepository.save(entity);
		}

		return result;
	}

	public void newCreat(String userId) {
		log.info("newCreat(userId = {})", userId);
		
		Assessment assessment = new Assessment(userId, 0, 0, 0, 0, 0);
		
		assessmentRepository.save(assessment);
	}

	
}
