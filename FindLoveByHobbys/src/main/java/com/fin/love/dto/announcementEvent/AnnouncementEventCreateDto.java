package com.fin.love.dto.announcementEvent;

import com.fin.love.repository.announcementEvent.AnnouncementEvent;

import lombok.Data;

@Data
public class AnnouncementEventCreateDto {
	
	private String title;
	private String author;
	private String content;
	private int divide;
	
	public AnnouncementEvent toEntity() {
		return AnnouncementEvent.builder()
				.title(title)
				.author(author)
				.content(content)
				.divide(divide)
				.build();
	}
	
	
}
