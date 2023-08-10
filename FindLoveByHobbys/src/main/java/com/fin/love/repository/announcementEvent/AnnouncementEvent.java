package com.fin.love.repository.announcementEvent;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // JPA 엔터티 클래스 - 데이터베이스 테이블과 매핑되는 클래스.
@Table(name = "ANNOUNCEMENT_EVENT") // 엔터티 클래스 이름이 데이터베이스 테이블 이름과 다른 경우, 테이블 이름을 명시.
@SequenceGenerator(name = "ANNOUNCEMENT_EVENT_SEQ_GEN", sequenceName = "ANNOUNCEMENT_EVENT_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class AnnouncementEvent extends BaseTimeEntity {
	
	@Id // Primary key 제약조건
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANNOUNCEMENT_EVENT_SEQ_GEN")
	private Long id;
	
    @Column(nullable = false)
	private String title;

    @Column(nullable = false)
	private String author;
	
    @Column(nullable = false)
	private String content;
	
    @Column(nullable = false)
    private int category;
    
    @Column(nullable = false)
    private int divide;
    
    // Setter 메서드의 역할을 한다.
    // AnnouncementEvent 엔터티의 title과 content를 수정해서 리턴하는 메서드:
    public AnnouncementEvent update(AnnouncementEventDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        
        return this;
    }
}
