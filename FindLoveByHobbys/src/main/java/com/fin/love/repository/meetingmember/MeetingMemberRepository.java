package com.fin.love.repository.meetingmember;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.repository.meeting.Meeting;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {

//	long findAverageProfileUserAgeByMeeting(Meeting meeting);
	
	List<MeetingMember> findByMeetingId(long id);
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM MEETINGMEMBER WHERE meetid = :meetid", nativeQuery = true)
	void deletedQuery(@Param("meetid") long meetid);
}
