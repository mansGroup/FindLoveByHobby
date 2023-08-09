package com.fin.love.repository.meetingmember;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fin.love.repository.meeting.Meeting;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {

//	long findAverageProfileUserAgeByMeeting(Meeting meeting);
	
	List<MeetingMember> findByMeetingId(long id);
	
}
