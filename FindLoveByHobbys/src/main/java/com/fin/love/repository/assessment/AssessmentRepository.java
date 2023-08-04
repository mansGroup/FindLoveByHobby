package com.fin.love.repository.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fin.love.dto.matching.MatchingDetailDto;

public interface AssessmentRepository extends JpaRepository<Assessment, String> {

	
	
}
