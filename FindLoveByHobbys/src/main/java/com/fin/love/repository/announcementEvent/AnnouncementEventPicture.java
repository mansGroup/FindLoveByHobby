package com.fin.love.repository.announcementEvent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ANNOUNCEMENT_EVENT_PICTURE")
@SequenceGenerator(name = "ANNOUNCEMENT_EVENT_PICTURE_SEQ_GEN", sequenceName = "ANNOUNCEMENT_EVENT_PICTURE_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AnnouncementEventPicture {
	
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String picture;
	
	
	
	public AnnouncementEventPicture picUpdate(String picture) {
		
		this.picture = picture;
		
		return this;
	}
	
	
}
