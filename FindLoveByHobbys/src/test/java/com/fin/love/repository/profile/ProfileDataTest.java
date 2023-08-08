package com.fin.love.repository.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProfileDataTest {
	
	@Autowired
	private AcademicRepository ar;
	
	@Autowired
	private IncomeRepository ir;
	
	@Autowired
	private JobsRepository jr;
	
	@Autowired
	private ReligionRepository rr;
	
	@Test
	public void profileData() {
		log.info("데이터 들어간다.");
		
		String[] academicArr = "4년제대학교 졸업,4년제대학교 재학중,전문대학교 졸업,전문대학교 재학중,고등학교 졸업".split(",");
		String[] jobArr = "학생,사업가,전문직,회사원,공무원,군인,교육직/연구직,금융직,기술직,기타".split(",");
		String[] incomeArr = "1000만원 이하/1,000만원 ~ 2,000만원/2,000만원 ~ 3,000만원/3,000만원 ~ 4,000만원/4,000만원 ~ 5,000만원/5,000만원 ~ 6,000만원/6,000만원 ~ 7,000만원/7,000만원 ~ 8,000만원/8,000만원 ~ 9,000만원/9,000만원 ~ 10,000만원/10,000만원 이상".split("/");
		String[] religionArr = "기독교,천주교,불교,무교".split(",");
		
//		for (String academic : academicArr) {
//			log.info(academic);
//			
//			Academic ac = Academic.builder().academicName(academic).build();/
//			
//			ar.save(ac);
//		}
		
		for (String job : jobArr) {
			log.info(job);
			
			Jobs jo = Jobs.builder().jobName(job).build();
			
			jr.save(jo);
		}
		
		for (String in : incomeArr) {
			log.info(in);
			
			Income income = Income.builder().income(in).build();
			
			ir.save(income);
		}
		
		for (String re : religionArr) {
			log.info(re);
			
			Religion religion = Religion.builder().religionName(re).build();
			
			rr.save(religion);
		}
		
		log.info("끝");
	}
	
}
