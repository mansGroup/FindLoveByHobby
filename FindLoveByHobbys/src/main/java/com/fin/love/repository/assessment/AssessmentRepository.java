package com.fin.love.repository.assessment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fin.love.dto.matching.MatchingDetailDto;

public interface AssessmentRepository extends JpaRepository<Assessment, String> {
	@Query( value = 
			"SELECT * FROM SKOTT.USER_HOBBY UH JOIN SKOTT.HOBBY H ON UH.HOBBY_ID = H.HOBBY_ID where UH.USERID = :uid",
			nativeQuery = true
	)
	
	List<MatchingDetailDto> findByHobbyise(@Param("uid") String uid);
	
}
