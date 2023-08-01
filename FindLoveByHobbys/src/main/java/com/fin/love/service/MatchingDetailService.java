package com.fin.love.service;

import java.util.List;

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

    // DB 테이블에서 id 불러오기
    @Transactional(readOnly = true)
    public Assessment read(Long id) {
        log.info("read(id={})", id);

        return assessmentRepository.findById(id).orElseThrow();
    }

    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    public Profile getUserProfile(String id) {
        return profileRepository.findById(id).orElseThrow();
    }

    public List<UserHobby> getUserHobbies(String id) {
        return userHobbyRepository.findByUserid(id);
    }
}
