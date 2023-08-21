package com.fin.love.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.fin.love.dto.matching.MatchingListDto;
import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingService {

	private final MemberRepository memberRepository;
	private final UserHobbyRepository userHobbyRepository;
	private final HobbyRepository hobbyRepository;
	private final ProfileRepository profileRepository;
	private final AssessmentRepository assessmentRepository;
	private final AgeRepository ageRepository;

	@Transactional(readOnly = true)
	public List<MatchingListDto> matching(String id) {
		log.info("matching()");

		// 로그인한 사용자 profile 들고오기
		Profile userProfile = profileRepository.findById(id).orElse(null);
		
		if (userProfile == null) {
			return null;
		}

		// 로그인한 사용자 userInfo 들고오기
		Member userInfo = memberRepository.findById(id).orElseThrow();

		// 로그인한 사용자 취미 들고 오기
		List<UserHobby> userHobbys = userHobbyRepository.findByUserid(id);

		// 로그인한 사용자 취미를 전체 취미 List와 대조해 정리한 List
		List<Integer> hobbyScore = new LinkedList<>();

		// 전체 회원 검색을 위한 list
		List<Member> members = null;
		
		// 나이 리스트
		List<Age> ages = ageRepository.findAll();

		log.info("사용자 성별 >>>> " + userInfo.getSex());
		
		Role role = Role.USER;
		
		if (userInfo.getSex() == 1) { 
			members = memberRepository.findBySexAndRole(2, role); // 여자만 검색
		} else { // 여자라면
			members = memberRepository.findBySexAndRole(1, role); // 남자만 검색
		}
		
		// 모든 취미 불러오는 List
		List<Hobby> hobbys = hobbyRepository.findAll();

		for (int i = 0; i < 75; i++) {
			for (int j = 0; j < userHobbys.size(); j++) {
				if (hobbys.get(i).getHobbyId() == userHobbys.get(j).getHobbyId()) {
					hobbyScore.add(1);
				} else {
					hobbyScore.add(0);
				}
			}
		}

		// 점수를 저장할 객체 생성.
		int score = 0;

		// 점수가 높은 5명을 고르기 위해 아이디 5개를 저장하는 객체 생성.
		String first = ""; // 가장 높은 점수
		String second = ""; // 두번째로 높은 점수
		String third = ""; // 세번째로 높은 점수
		String fourth = ""; // 네번째
		String fifth = ""; // 다섯번째

		// 취미 매칭하기
		for (int i = 0; i < members.size(); i++) {
			int matchingMemberScore = 0;

			log.info("members >>>>>> " + members.get(i).toString());
			log.info("members.get({}).getId({})", members.get(i), members.get(i).getId());

			// 취미
			List<UserHobby> matchingMemberHobby = userHobbyRepository.findByUserid(members.get(i).getId());

			for (int j = 0; j < matchingMemberHobby.size(); j++) {
				// 같은 취미를 가지는지 확인을 위한 객체 생성.
				Long num = matchingMemberHobby.get(j).getHobbyId();
				int number = Integer.parseInt(String.valueOf(num));

				if (hobbyScore.get(number) == 1) { // 1이면 같은 취미
					matchingMemberScore += 10;
				}
			}

			Profile memberProfile = profileRepository.findById(members.get(i).getId()).orElseThrow();

			log.info("memberProfile >>>> " + memberProfile);

			// 직업
			matchingMemberScore += jobCalculation(userProfile.getUserJob(), memberProfile.getUserJob());

			// 연봉
			matchingMemberScore += incomeCalculation(userProfile.getUserIncome(), memberProfile.getUserIncome());

			// 학교
			matchingMemberScore += academicCalculation(userProfile.getUserAcademic(), memberProfile.getUserAcademic());

			// 매칭률이 높으면 String 객체에 저장하기 위한 조건식.
			if (score < matchingMemberScore) {
				fifth = fourth;
				fourth = third;
				third = second;
				second = first;
				first = members.get(i).getId();
			}
		}

		log.info("first >>> " + first);
		log.info("second >>> " + second);
		log.info("third >>> " + third);
		log.info("fourth >>> " + fourth);
		log.info("fifth >>> " + fifth);

		// 리터할 List
		List<MatchingListDto> list = new LinkedList<>();

		// 첫번째 매칭 유저
		if (!first.equals("")) {
			Assessment assessmentFirst = assessmentRepository.findById(first).orElse(null);
			String assessmentFirtsName = "";
			int assessmentFirtsCount = 0;
			String assessmentFirstStr = assessmentMaxValue(assessmentFirst);
			
			if (assessmentFirst.getBeautiful() == 0 && assessmentFirst.getCute() == 0 && assessmentFirst.getHandsome() == 0 
					&& assessmentFirst.getSexy() == 0 && assessmentFirst.getWonderful() == 0) {
				assessmentFirtsName = "Commonness";
			} else if (assessmentFirstStr != null) {
				StringTokenizer st = new StringTokenizer(assessmentFirstStr, "/");
				assessmentFirtsName = st.nextToken();
				assessmentFirtsCount = Integer.parseInt(st.nextToken());
			}
			String memberNickname1 = memberRepository.findById(first).orElseThrow().getNickname(); // 첫번째 매칭된 유저 닉네임
			String memberage1 = ages.get(profileRepository.findById(first).orElseThrow().getUserAge() - 1).getAgeName(); // 첫번째 매칭된 유저 나이
			String memberIntrodus1 = profileRepository.findById(first).orElseThrow().getUserIntroduce(); // 첫번째 매칭된 유저
			
			MatchingListDto firstDto = new MatchingListDto(first, memberNickname1, memberage1, memberIntrodus1,
					assessmentFirtsName, assessmentFirtsCount);
			list.add(firstDto);
		}

		// 두번째 매칭 유저
		if (!second.equals("")) {
			Assessment assessmentSecond = assessmentRepository.findById(second).orElse(null);
			String assessmentSecondName = "";
			int assessmentSecondCount = 0;
			String assessmentSecondStr = assessmentMaxValue(assessmentSecond);
			if (assessmentSecond.getBeautiful() == 0 && assessmentSecond.getCute() == 0 && assessmentSecond.getHandsome() == 0 
					&& assessmentSecond.getSexy() == 0 && assessmentSecond.getWonderful() == 0) {
				assessmentSecondName = "Commonness";
			} else if (assessmentSecondStr != null) {
				StringTokenizer st = new StringTokenizer(assessmentSecondStr, "/");
				assessmentSecondName = st.nextToken();
				assessmentSecondCount = Integer.parseInt(st.nextToken());
			}
			String memberNickname2 = memberRepository.findById(second).orElseThrow().getNickname(); // 두번째 매칭된 유저 닉네임
			String memberage2 = ages.get(profileRepository.findById(second).orElseThrow().getUserAge() - 1).getAgeName(); // 두번째 매칭된 유저 나이
			String memberIntrodus2 = profileRepository.findById(second).orElseThrow().getUserIntroduce(); // 두번째 매칭된 유저
																											// 소개

			MatchingListDto secondDto = new MatchingListDto(second, memberNickname2, memberage2, memberIntrodus2,
					assessmentSecondName, assessmentSecondCount);
			list.add(secondDto);
		}

		// 세번째 매칭 유저
		if (!third.equals("")) {
			Assessment assessmentThird = assessmentRepository.findById(third).orElse(null);
			String assessmentThirdName = "";
			int assessmentThirdCount = 0;
			String assessmentThirdStr = assessmentMaxValue(assessmentThird);
			if (assessmentThird.getBeautiful() == 0 && assessmentThird.getCute() == 0 && assessmentThird.getHandsome() == 0 
					&& assessmentThird.getSexy() == 0 && assessmentThird.getWonderful() == 0) {
				assessmentThirdName = "Commonness";
			} else if (assessmentThirdStr != null) {
				StringTokenizer st = new StringTokenizer(assessmentThirdStr, "/");
				assessmentThirdName = st.nextToken();
				assessmentThirdCount = Integer.parseInt(st.nextToken());
			}
			String memberNickname3 = memberRepository.findById(third).orElseThrow().getNickname(); // 세번째 매칭된 유저 닉네임
			String memberage3 = ages.get(profileRepository.findById(third).orElseThrow().getUserAge() - 1).getAgeName(); // 세번째 매칭된 유저 나이
			String memberIntrodus3 = profileRepository.findById(third).orElseThrow().getUserIntroduce(); // 세번째 매칭된 유저
																											// 소개

			MatchingListDto thirdDto = new MatchingListDto(third, memberNickname3, memberage3, memberIntrodus3,
					assessmentThirdName, assessmentThirdCount);
			list.add(thirdDto);
		}

		// 네번째 매칭 유저
		if (!fourth.equals("")) {
			Assessment assessmentFourth = assessmentRepository.findById(fourth).orElse(null);
			String assessmentFourthName = "";
			int assessmentFourthCount = 0;
			String assessmentFourthStr = assessmentMaxValue(assessmentFourth);
			if (assessmentFourth.getBeautiful() == 0 && assessmentFourth.getCute() == 0 && assessmentFourth.getHandsome() == 0 
					&& assessmentFourth.getSexy() == 0 && assessmentFourth.getWonderful() == 0) {
				assessmentFourthName = "Commonness";
			} else if (assessmentFourthStr != null) {
				StringTokenizer st = new StringTokenizer(assessmentFourthStr, "/");
				assessmentFourthName = st.nextToken();
				assessmentFourthCount = Integer.parseInt(st.nextToken());
			}
			String memberNickname4 = memberRepository.findById(fourth).orElseThrow().getNickname(); // 네번째 매칭된 유저 닉네임
			String memberage4 = ages.get(profileRepository.findById(fourth).orElseThrow().getUserAge() - 1).getAgeName(); // 네번째 매칭된 유저 나이
			String memberIntrodus4 = profileRepository.findById(fourth).orElseThrow().getUserIntroduce(); // 네번째 매칭된 유저
																											// 소개

			MatchingListDto fourthDto = new MatchingListDto(fourth, memberNickname4, memberage4, memberIntrodus4,
					assessmentFourthName, assessmentFourthCount);
			list.add(fourthDto);
		}

		// 다섯 번재 매칭 유저
		if (!fifth.equals("")) {
			Assessment assessmentFifth = assessmentRepository.findById(fifth).orElse(null);
			String assessmentFifthName = "";
			int assessmentFifthCount = 0;
			String assessmentFifthStr = assessmentMaxValue(assessmentFifth);
			if (assessmentFifth.getBeautiful() == 0 && assessmentFifth.getCute() == 0 && assessmentFifth.getHandsome() == 0 
					&& assessmentFifth.getSexy() == 0 && assessmentFifth.getWonderful() == 0) {
				assessmentFifthName = "Commonness";
			} else if (assessmentFifthStr != null) {
				StringTokenizer st = new StringTokenizer(assessmentFifthStr, "/");
				assessmentFifthName = st.nextToken();
				assessmentFifthCount = Integer.parseInt(st.nextToken());
			}
			String memberNickname5 = memberRepository.findById(fifth).orElseThrow().getNickname(); // 다섯번째 매칭된 유저 닉네임
			String memberage5 = ages.get(profileRepository.findById(fifth).orElseThrow().getUserAge() - 1).getAgeName(); // 다섯번째 매칭된 유저 나이
			String memberIntrodus5 = profileRepository.findById(fifth).orElseThrow().getUserIntroduce(); // 다섯번째 매칭된 유저
																											// 소개

			MatchingListDto fifthDto = new MatchingListDto(fifth, memberNickname5, memberage5, memberIntrodus5,
					assessmentFifthName, assessmentFifthCount);
			list.add(fifthDto);
		}

		// 검색 대상이 없을 경우
		if (first.equals("") && second.equals("") && third.equals("") && fourth.equals("") && fifth.equals("")) {
			if (userInfo.getSex() == 1) { 
				members = memberRepository.findBySexAndRole(2, role); // 여자만 검색

				for (int i = 0; i < members.size(); i++) {
					Assessment assessmentMember = assessmentRepository.findById(members.get(i).getId()).orElseThrow();
					String assessmentMemberStr = assessmentMaxValue(assessmentMember);
					String assessmentMemberName = "";
					int assessmentMemberCount = 0;
					if (assessmentMemberStr != null) {
						StringTokenizer st = new StringTokenizer(assessmentMemberStr, "/");
						assessmentMemberName = st.nextToken();
						assessmentMemberCount = Integer.parseInt(st.nextToken());
					}
					
					Member ranMember = memberRepository.findById(members.get(i).getId()).orElseThrow();
					
					String memberNickname = memberRepository.findById(members.get(i).getId()).orElseThrow()
							.getNickname(); // 다섯번째 매칭된 유저 닉네임
					String memberage = ages.get(profileRepository.findById(members.get(i).getId()).orElseThrow().getUserAge() - 1).getAgeName(); 
					
					String memberIntrodus = profileRepository.findById(members.get(i).getId()).orElseThrow()
							.getUserIntroduce(); // 다섯번째 매칭된 유저 소개

					MatchingListDto member = new MatchingListDto(members.get(i).getId(), memberNickname, memberage,
							memberIntrodus, assessmentMemberName, assessmentMemberCount);
					
					if (ranMember.getRole() == Role.USER) {
						list.add(member);
					}
				}
			} else { // 여자라면
				members = memberRepository.findBySexAndRole(1, role); // 남자만 검색

				for (int i = 0; i < members.size(); i++) {
					Assessment assessmentMember = assessmentRepository.findById(members.get(i).getId()).orElseThrow();
					String assessmentMemberStr = assessmentMaxValue(assessmentMember);
					String assessmentMemberName = "";
					int assessmentMemberCount = 0;
					if (assessmentMemberStr != null) {
						StringTokenizer st = new StringTokenizer(assessmentMemberStr, "/");
						assessmentMemberName = st.nextToken();
						assessmentMemberCount = Integer.parseInt(st.nextToken());
					}
					
					Member ranMember = memberRepository.findById(members.get(i).getId()).orElseThrow();

					String memberNickname = memberRepository.findById(members.get(i).getId()).orElseThrow()
							.getNickname(); // 다섯번째 매칭된 유저 닉네임
					String memberage = ages.get(profileRepository.findById(members.get(i).getId()).orElseThrow().getUserAge()).getAgeName();
					String memberIntrodus = profileRepository.findById(members.get(i).getId()).orElseThrow()
							.getUserIntroduce(); // 다섯번째 매칭된 유저 소개

					MatchingListDto member = new MatchingListDto(members.get(i).getId(), memberNickname, memberage,
							memberIntrodus, assessmentMemberName, assessmentMemberCount);

					if (ranMember.getRole() == Role.USER) {
						list.add(member);
					}
				}
			}
		}
		
		

		return list;
	}

	// 호감도가 제일 높은 걸 찾기 위한 메서드
	private String assessmentMaxValue(Assessment assessment) {
		String str = "";
		int max = 0;

		if (assessment.getBeautiful() > assessment.getHandsome() && assessment.getBeautiful() > assessment.getSexy()
				&& assessment.getBeautiful() >= assessment.getWonderful()
				&& assessment.getBeautiful() >= assessment.getCute()) {
			str = "Beautiful";
			max = assessment.getBeautiful();
		} else if (assessment.getCute() >= assessment.getBeautiful() && assessment.getCute() >= assessment.getHandsome()
				&& assessment.getCute() >= assessment.getSexy() && assessment.getCute() >= assessment.getWonderful()) {
			str = "Cute";
			max = assessment.getCute();
		} else if (assessment.getHandsome() > assessment.getBeautiful()
				&& assessment.getHandsome() >= assessment.getCute() && assessment.getHandsome() > assessment.getSexy()
				&& assessment.getHandsome() >= assessment.getWonderful()) {
			str = "Handsome";
			max = assessment.getHandsome();
		} else if (assessment.getSexy() >= assessment.getBeautiful() && assessment.getSexy() >= assessment.getCute()
				&& assessment.getSexy() >= assessment.getHandsome()
				&& assessment.getSexy() >= assessment.getWonderful()) {
			str = "Sexy";
			max = assessment.getSexy();
		} else if (assessment.getWonderful() >= assessment.getBeautiful()
				&& assessment.getWonderful() >= assessment.getCute()
				&& assessment.getWonderful() >= assessment.getHandsome()
				&& assessment.getWonderful() >= assessment.getSexy()) {
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

	// 직업 매칭율에 따른 점수 계산하는 메서드
	private int jobCalculation(int userJobNumber, int matchingMemberJobNumber) {
		int score = 0;

		if (userJobNumber == 1) {
			if (matchingMemberJobNumber == 1) {
				score = 10;
			} else if (matchingMemberJobNumber == 2) {
				score = 4;
			} else if (matchingMemberJobNumber == 3) {
				score = 5;
			} else if (matchingMemberJobNumber == 4) {
				score = 8;
			} else if (matchingMemberJobNumber == 5) {
				score = 7;
			} else if (matchingMemberJobNumber == 6) {
				score = 1;
			} else if (matchingMemberJobNumber == 7) {
				score = 6;
			} else if (matchingMemberJobNumber == 8) {
				score = 6;
			} else if (matchingMemberJobNumber == 9) {
				score = 6;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 2) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 10;
			} else if (matchingMemberJobNumber == 3) {
				score = 7;
			} else if (matchingMemberJobNumber == 4) {
				score = 6;
			} else if (matchingMemberJobNumber == 5) {
				score = 5;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 6;
			} else if (matchingMemberJobNumber == 8) {
				score = 8;
			} else if (matchingMemberJobNumber == 9) {
				score = 7;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 3) {
			if (matchingMemberJobNumber == 1) {
				score = 6;
			} else if (matchingMemberJobNumber == 2) {
				score = 5;
			} else if (matchingMemberJobNumber == 3) {
				score = 10;
			} else if (matchingMemberJobNumber == 4) {
				score = 5;
			} else if (matchingMemberJobNumber == 5) {
				score = 6;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 7;
			} else if (matchingMemberJobNumber == 8) {
				score = 6;
			} else if (matchingMemberJobNumber == 9) {
				score = 5;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 4) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 6;
			} else if (matchingMemberJobNumber == 3) {
				score = 4;
			} else if (matchingMemberJobNumber == 4) {
				score = 10;
			} else if (matchingMemberJobNumber == 5) {
				score = 6;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 6;
			} else if (matchingMemberJobNumber == 8) {
				score = 7;
			} else if (matchingMemberJobNumber == 9) {
				score = 6;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 5) {
			if (matchingMemberJobNumber == 1) {
				score = 4;
			} else if (matchingMemberJobNumber == 2) {
				score = 7;
			} else if (matchingMemberJobNumber == 3) {
				score = 6;
			} else if (matchingMemberJobNumber == 4) {
				score = 6;
			} else if (matchingMemberJobNumber == 5) {
				score = 10;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 8;
			} else if (matchingMemberJobNumber == 8) {
				score = 7;
			} else if (matchingMemberJobNumber == 9) {
				score = 5;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 6) {
			if (matchingMemberJobNumber == 1) {
				score = 5;
			} else if (matchingMemberJobNumber == 2) {
				score = 5;
			} else if (matchingMemberJobNumber == 3) {
				score = 5;
			} else if (matchingMemberJobNumber == 4) {
				score = 5;
			} else if (matchingMemberJobNumber == 5) {
				score = 8;
			} else if (matchingMemberJobNumber == 6) {
				score = 10;
			} else if (matchingMemberJobNumber == 7) {
				score = 7;
			} else if (matchingMemberJobNumber == 8) {
				score = 7;
			} else if (matchingMemberJobNumber == 9) {
				score = 5;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 7) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 7;
			} else if (matchingMemberJobNumber == 3) {
				score = 7;
			} else if (matchingMemberJobNumber == 4) {
				score = 7;
			} else if (matchingMemberJobNumber == 5) {
				score = 7;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 10;
			} else if (matchingMemberJobNumber == 8) {
				score = 7;
			} else if (matchingMemberJobNumber == 9) {
				score = 6;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 8) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 8;
			} else if (matchingMemberJobNumber == 3) {
				score = 6;
			} else if (matchingMemberJobNumber == 4) {
				score = 5;
			} else if (matchingMemberJobNumber == 5) {
				score = 6;
			} else if (matchingMemberJobNumber == 6) {
				score = 3;
			} else if (matchingMemberJobNumber == 7) {
				score = 6;
			} else if (matchingMemberJobNumber == 8) {
				score += 10;
			} else if (matchingMemberJobNumber == 9) {
				score = 6;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 9) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 8;
			} else if (matchingMemberJobNumber == 3) {
				score = 7;
			} else if (matchingMemberJobNumber == 4) {
				score = 6;
			} else if (matchingMemberJobNumber == 5) {
				score = 7;
			} else if (matchingMemberJobNumber == 6) {
				score = 2;
			} else if (matchingMemberJobNumber == 7) {
				score = 7;
			} else if (matchingMemberJobNumber == 8) {
				score = 8;
			} else if (matchingMemberJobNumber == 9) {
				score = 10;
			} else if (matchingMemberJobNumber == 10) {
				score = 3;
			}

		} else if (userJobNumber == 10) {
			if (matchingMemberJobNumber == 1) {
				score = 3;
			} else if (matchingMemberJobNumber == 2) {
				score = 5;
			} else if (matchingMemberJobNumber == 3) {
				score = 5;
			} else if (matchingMemberJobNumber == 4) {
				score = 5;
			} else if (matchingMemberJobNumber == 5) {
				score = 5;
			} else if (matchingMemberJobNumber == 6) {
				score = 4;
			} else if (matchingMemberJobNumber == 7) {
				score = 5;
			} else if (matchingMemberJobNumber == 8) {
				score = 5;
			} else if (matchingMemberJobNumber == 9) {
				score = 5;
			} else if (matchingMemberJobNumber == 10) {
				score += 10;
			}

		}

		return score;
	}

	// 연봉 매칭율에 따른 점수 계산하는 메서드
	private int incomeCalculation(int userIncomeNumber, int matchingMemberIncomeNumber) {
		int score = 0;

		int min = userIncomeNumber - matchingMemberIncomeNumber;

		if (min == 0) {
			score = 10;
		} else if (min == -2) {
			score = 3;
		} else if (min == -1) {
			score = 7;
		} else if (min == 1) {
			score = 8;
		} else if (min == 2) {
			score = 5;
		} else if (min == 3) {
			score = 1;
		}

		return score;
	}

	// 학교 매칭율에 따른 점수 계산하는 메서드
	private int academicCalculation(int useracademicNumber, int matchingMemberacademicNumber) {
		int score = 0;

		if (useracademicNumber == matchingMemberacademicNumber) {
			score = 10;
		} else if (matchingMemberacademicNumber == 1 && matchingMemberacademicNumber == 2) {
			score = 8;
		} else if (matchingMemberacademicNumber == 2 && matchingMemberacademicNumber == 1) {
			score = 8;
		} else if (matchingMemberacademicNumber == 3 && matchingMemberacademicNumber == 4) {
			score = 8;
		} else if (matchingMemberacademicNumber == 4 && matchingMemberacademicNumber == 3) {
			score = 8;
		} else if (matchingMemberacademicNumber == 5) {
			score = 8;
		}

		return score;
	}

	// 출력하는 사진으로 변경시키기
	public String imageChange(String picture) {
		log.info("imageChange(picture = {})", picture);

		if (picture.equals("/images/Adding_a_Person_Image.png")) {
			return picture;
		}

		String result = "/images/uploadImages/";
		result += picture;

		return result;
	}

	// 매칭 리스트에서 랜덤으로 2명을 선정하는 매서드
	public List<MatchingListDto> matchingBest(List<MatchingListDto> list) {
		List<MatchingListDto> result = new ArrayList<>();

		Random ran = new Random();

		int number = 0;

		for (int i = 0; i < 2; i++) {
			int randomInt = ran.nextInt(list.size());

			if (number != randomInt) {
				number = randomInt;
				result.add(list.get(randomInt));
			} else {
				i--;
			}
		}

		return result;
	}

}
