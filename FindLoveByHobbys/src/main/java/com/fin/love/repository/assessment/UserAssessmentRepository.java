package com.fin.love.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAssessmentRepository  extends JpaRepository<UserAssessment, Long>{

	UserAssessment findBySenderAndGetter(String sender, String getter);

	
}
