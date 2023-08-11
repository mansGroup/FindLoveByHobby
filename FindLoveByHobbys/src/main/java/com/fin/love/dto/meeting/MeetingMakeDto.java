package com.fin.love.dto.meeting;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.meeting.Meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingMakeDto {

	private String leader;
	private long hobbyid;
	private String title;
	private String content1;
	private String content2;
	private String content3;
	private String image1;
	private String image2;
	private String image3;
	private String contents;
	private long locationid;
	private int status;
	private LocalDateTime meetingtime;
	
	
	public Meeting toEntity(Location loc, Hobby hobby) {
		// TODO Auto-generated method stub
		return Meeting.builder().content1(content1).content2(content2).content3(content3).contents(contents).leader(leader).title(title).location(loc).hobby(hobby).image1(image1).image2(image2).image3(image3).title(title).status(status).meetingdate(meetingtime).build();
	}
	
}
