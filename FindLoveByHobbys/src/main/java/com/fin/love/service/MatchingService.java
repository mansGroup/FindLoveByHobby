package com.fin.love.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.matching.MatchingListDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

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
	
	@Transactional(readOnly = true)
	public List<MatchingListDto> matching(String id) {
		log.info("matching()");
		
		// 전체 회원 검색을 위한 list
		List<Member> members = memberRepository.findAll(); // 남자면 여자만, 여자면 남자만 불러오게 만들기.
		
		// 모든 취미 불러오는 List
		List<Hobby> hobbys = hobbyRepository.findAll();
		
		// 로그인한 사용자 profile 들고오기
		Profile userProfile = profileRepository.findById(id).orElseThrow();
		
		// 로그인한 사용자 취미 들고 오기
		List<UserHobby> userHobbys = userHobbyRepository.findByUserid(id);
		
		// 로그인한 사용자 취미를 전체 취미 List와 대조해 정리한 List
		List<Integer> hobbyScore = new LinkedList<>();
		
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
		
		// 점수가 높은 2명을 고르기 위해 아이디 2개를 저장하는 객체 생성.
		String first = ""; // 가장 높은 점수
		String second = ""; // 두번째로 높은 점수
		
		// 취미 매칭하기
		for (int i = 0; i < members.size(); i++) {
			int matchingMemberScore = 0;
			
			// 취미
			List<UserHobby> matchingMemberHobby = userHobbyRepository.findByUserid(members.get(i).getId());
			
			for (int j = 0; j < matchingMemberHobby.size(); i++) {
				// 같은 취미를 가지는지 확인을 위한 객체 생성.
				int number = matchingMemberHobby.get(j).getHobbyId();
				
				if (hobbyScore.get(number) == 1) { // 1이면 같은 취미
					matchingMemberScore += 10;
				}
			}
			
			Profile memberProfile = profileRepository.findById(matchingMemberHobby.get(i).getUserid()).orElseThrow();
			
			// 직업
			matchingMemberScore += jobCalculation(userProfile.getUserJob(), memberProfile.getUserJob());
			
			// 연봉
			matchingMemberScore += incomeCalculation(userProfile.getUserIncome(), memberProfile.getUserIncome());
			
			// 학교
			matchingMemberScore += academicCalculation(userProfile.getUserAcademic(), memberProfile.getUserAcademic());
			
			// 매칭률이 높으면 String 객체에 저장하기 위한 조건식.
			if (score > matchingMemberScore) {
				// 제일 높은 사람을 2번재로 저장.
				second = first;
				first = members.get(i).getId();
			}
		}
		
		// 리터할 List
		List<MatchingListDto> list = new LinkedList<>();
		
		// matchinglistdto 객체 추가 하면 생성해서 2개를 리슽화 시켜서 리턴하기.
		String memberNickname1 = memberRepository.findById(first).orElseThrow().getNickname(); // 첫번째 매칭된 유저 닉네임
		int memberage1 = profileRepository.findById(first).orElseThrow().getUserAge(); // 첫번째 매칭된 유저 나이
		// assessment
		
		String memberNickname2 = memberRepository.findById(second).orElseThrow().getNickname(); // 두번째 매칭된 유저 닉네임
		int memberage2 = profileRepository.findById(second).orElseThrow().getUserAge(); // 두번째 매칭된 유저 나이
		// assessment
		
		return list;
	}
	
	// 직업 매칭율에 따른 점수 계산하는 메서드
	public int jobCalculation(int userJobNumber, int matchingMemberJobNumber) {
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
	public int incomeCalculation (int userIncomeNumber, int matchingMemberIncomeNumber) {
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
	public int academicCalculation (int useracademicNumber, int matchingMemberacademicNumber) {
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
	
}
