package com.fin.love.repository.hobby;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;



@Slf4j
@SpringBootTest
public class HobbyRepositoryTest {
	
	@Autowired
	private HobbyRepository hobbyRepository;
	
	@Autowired
	private UserHobbyRepository uhr;
	
	@Test
	public void testCreated() {
		log.info("testCreated() 실행 ");
		
		String str = "운동,자전거,댄스,레저,산책,등산,골프,헬스,필라테스,요가,홈트,클라이밍,수영,볼링,당구,배드민턴,농구,캠핑,국내여행,해외여행,전시회관람,봉사활동,드라이브,사진촬영,공연관람,쇼핑,연극,영화,오페라,뮤지컬,환경보호활동,드라마,글쓰기,공부,외국어/어학,it,덕질,k-pop덕질,그림그리기,인테리어,애니,게임,LOL,배틀그라운드,오버워치,문학,재테크,음악감상,노래부르기.악기연주,웹툰,독서,넷플릭스,TV예능,홈카페,코인노래방,수다,떡볶이맛집,고기맛집,야구보기,축구보기,농구보기,카공,자격증따기,원데이클래스,멍때리기,반려식물,뜨개질,사주/타로,요리,맥주,맛집투어,혼술,술,카페가기,베이킹";
		String[] arr = str.split(",");
		
		for (String s : arr) {
			log.info(s);
			
			Hobby hobby = Hobby.builder()
					.content("1")
					.hobbyName(s)
					.build();
			
			hobbyRepository.save(hobby);
		}
		
		List<Hobby> list = hobbyRepository.findAll();
		
		for (Hobby h : list) {
			log.info(h.toString());
		}
		
		log.info("종료");
	}
	
//	@Test
	public void findByUserIdTest() {
		log.info("findByUserIdTest()");
		
		String userId = "111";
		
		List<UserHobby> list = uhr.findByUserid(userId);
		
		for (UserHobby uh : list) {
			log.info("UserHobby Data >>>> " + uh);
		}
		
	}
	
}
