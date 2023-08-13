package com.fin.love.repository.announcementEvent;

import com.fin.love.repository.image.Picture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ANNOUNCEMENT_EVENT_PICTURE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class AnnouncementEventPicture {
	
	@Id
	private String id;
	
	@Column
	private String picture;
	
	public AnnouncementEventPicture picUpdate(String picture) {
		
		this.picture = picture;
		
		return this;
	}
	
	
}
