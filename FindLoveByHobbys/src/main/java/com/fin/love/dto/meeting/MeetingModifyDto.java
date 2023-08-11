package com.fin.love.dto.meeting;

import com.fin.love.repository.meeting.Meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingModifyDto {

	private Meeting meeting;
	
	private String image1;
	
	private String image2;
	
	private String image3;
	
	public void imgMake(Meeting meet) {
		
		this.image1 = meet.getImage1();
		this.image2 = meet.getImage2();
		this.image3 = meet.getImage3();
		
	}
	
	public void entityMake(Meeting meet) {
		
		this.meeting = meet;
		
	}
	
	
}
