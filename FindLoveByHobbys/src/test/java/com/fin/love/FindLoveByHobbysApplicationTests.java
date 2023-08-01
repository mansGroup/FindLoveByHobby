package com.fin.love;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FindLoveByHobbysApplicationTests {

	@Test
	void contextLoads() {
		
		Map<String, Set<Integer>> map = new HashMap<>();
		
		System.out.println(map.get("D"));
		
	}

}
