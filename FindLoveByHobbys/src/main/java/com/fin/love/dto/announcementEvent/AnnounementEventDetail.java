package com.fin.love.dto.announcementEvent;

import com.fin.love.repository.announcementEvent.AnnouncementEvent;

import lombok.Data;

@Data

public class AnnounementEventDetail {
	
	private String title;
	private String author;
	private String content;
	private Long picture;
	private int divide;
	

	public AnnouncementEvent toEntity() {
		return AnnouncementEvent.builder()
				.title(title)
				.author(author)
				.content(content)
				.id(picture)
				.divide(divide)
				.build();
	}
}
