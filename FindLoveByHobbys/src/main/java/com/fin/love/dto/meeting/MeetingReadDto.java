package com.fin.love.dto.meeting;

import java.util.List;

import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.meetingmember.MeetingMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingReadDto {

	private MeetingMember meet;
	
	private String img;
	
	
	public static MeetingReadDto FromEntity(String img, MeetingMember meet) {
		
		return MeetingReadDto.builder().img(img).meet(meet).build();
		
		
	}
	
}
