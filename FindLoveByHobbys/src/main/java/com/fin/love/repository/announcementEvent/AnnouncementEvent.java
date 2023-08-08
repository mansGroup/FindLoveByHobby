package com.fin.love.repository.announcementEvent;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.BaseTimeEntity;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ANNOUNCEMENT_EVENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnnouncementEvent extends BaseTimeEntity {
	
	@Id
	private String id;
	
    @Column(nullable = false)
	private String title;

    @Column(nullable = false)
	private String author;
	
    @Column(nullable = false)
	private String content;
	
    // Setter 메서드의 역할을 한다.
    // AnnouncementEvent 엔터티의 title과 content를 수정해서 리턴하는 메서드:
    public AnnouncementEvent update(AnnouncementEventDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        
        return this;
    }
}
