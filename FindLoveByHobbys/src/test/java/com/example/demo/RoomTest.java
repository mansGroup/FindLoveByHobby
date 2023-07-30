package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RoomTest {

	@Test
	public void test1() {
		
		Map<String, Set<Integer>> map = new HashMap<>();
		
		log.info("{}",map.get("D"));
		
		
		
	}
	
}
