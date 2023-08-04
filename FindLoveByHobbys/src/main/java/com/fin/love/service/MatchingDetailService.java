package com.fin.love.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.assessment.Assessment;
import com.fin.love.repository.assessment.AssessmentRepository;
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
@RequiredArgsConstructor
@Service
public class MatchingDetailService {

    // 생성자를 사용한 의존성 주입:
    private final AssessmentRepository assessmentRepository;
    private final ProfileRepository profileRepository;
    private final HobbyRepository hobbyRepository;
    private final UserHobbyRepository userHobbyRepository;
    private final MemberRepository memberRepository; 
    
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
    
    // UserHobby와 Hobby에 Id를 비교 후 같다면 취미 목록을 출력한다.
    public List<String> getUserHobbies(String id) {
        List<UserHobby> list1 = userHobbyRepository.findByUserid(id);
        List<Hobby> list2 = getAllHobbies();
        List<String> hobbylist = new ArrayList<>();
        
        for(UserHobby x : list1) {
        	
        	for(Hobby y : list2) {
        		
        		if(x.getHobbyId() == y.getHobbyId()) {
        			
        			hobbylist.add(y.getHobbyName());
        			break;
        		}
        		
        	}
        	
        }
        
        return hobbylist;
        
        
    }
    
    // 유저의 회원가입 정보를 가져오기 위해
    public Member getUserinfo(String id){
    	log.info("id = {}", id);
    	
        return memberRepository.findById(id).orElseThrow();
    }
    
    // 
	public int assessment(String memberId) {
		// TODO: 
		return 0;
	}

}
