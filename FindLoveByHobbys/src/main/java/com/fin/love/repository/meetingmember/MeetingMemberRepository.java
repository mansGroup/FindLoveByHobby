package com.fin.love.repository.meetingmember;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fin.love.repository.meeting.Meeting;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {

//	long findAverageProfileUserAgeByMeeting(Meeting meeting);
	
	List<MeetingMember> findByMeetingId(long id);
	
	@Query(value = "DELETE FROM MEETINGMEMBER WHERE meetid = :meetid", nativeQuery = true)
	void deleteAllByMeetingId(@Param("meetid") long meetid);
}
