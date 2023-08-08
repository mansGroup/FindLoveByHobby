package com.fin.love.service.profile;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.profile.Income;
import com.fin.love.repository.profile.IncomeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncomeService {

	private final IncomeRepository incomeRepository;
	
	// 연봉 리스트
	@Transactional(readOnly = true)
	public List<Income> readIncomeList() {

		log.info("readIncomeList()");

		return incomeRepository.findAll();
	}
}
