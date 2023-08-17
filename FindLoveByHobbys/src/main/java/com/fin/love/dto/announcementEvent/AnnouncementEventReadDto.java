package com.fin.love.dto.announcementEvent;

import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.repository.announcementEvent.AnnouncementEventPicture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementEventReadDto {

	private String pictures;
	
	private AnnouncementEvent events;
	
	
	public static AnnouncementEventReadDto FromEntity(AnnouncementEvent events, String pictures) {
		
		return AnnouncementEventReadDto.builder().events(events).pictures(pictures).build();
		
	}
	
}
