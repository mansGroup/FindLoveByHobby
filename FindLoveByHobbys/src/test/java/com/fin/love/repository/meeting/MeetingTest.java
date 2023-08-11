package com.fin.love.repository.meeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fin.love.repository.location.Location;
import com.fin.love.repository.location.LocationRepository;

@SpringBootTest
public class MeetingTest {

	@Autowired
	private LocationRepository locrepository;
	
//	@Test
	public void locsave() {
		
		String[] loclist = {"서울 남부( 강남, 관악, 송파 등 )","서울 북부( 마포, 종로, 중랑 등 )", "경기 북부( 의정부, 파주, 양주 등 )"
		, "경기 남부 ( 수원, 용인, 성남 등 )", "충청북도", "충청남도", "경상북도","경상남도","강원도","전라북도","전라남도",
		"인천","대전","대구","울산","부산","광주"};
		
		for(String x : loclist) {
			
			Location loc = Location.builder().locationname(x).build();
			
			locrepository.save(loc);
			
		}
		
	}
	
}
