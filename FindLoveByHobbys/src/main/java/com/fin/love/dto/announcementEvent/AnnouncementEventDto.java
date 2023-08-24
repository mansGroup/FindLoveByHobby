package com.fin.love.dto.announcementEvent;

import com.fin.love.repository.announcementEvent.AnnouncementEvent;

import lombok.Data;

@Data
public class AnnouncementEventDto {
	
	private String title;
	private String author;
	private String content;
	private Long id;
	private int divide;
	
	public AnnouncementEvent toEntity() {
		return AnnouncementEvent.builder()
				.title(title)
				.author(author)
				.content(content)
				.id(id)
				.divide(divide)
				.build();
	}
}
