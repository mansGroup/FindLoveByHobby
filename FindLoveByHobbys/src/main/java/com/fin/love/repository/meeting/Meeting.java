package com.fin.love.repository.meeting;

import java.time.LocalDateTime;
import java.util.Deque;

import com.fin.love.dto.meeting.MeetingMakeDto;
import com.fin.love.repository.BaseTimeEntity;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "meeting")
@SequenceGenerator(name = "MEETING_SEQ_GEN", sequenceName = "MEETING_SEQ" ,allocationSize = 1)
public class Meeting extends BaseTimeEntity {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEETING_SEQ_GEN")
	private long id;
	
	@Column
	private String leader;
	
	@Column
	private int member;
	
	@Column
	private int status;
	
	@JoinColumn(name = "hobby")
	@ManyToOne
	private Hobby hobby;
	
	@Column
	private String title;
	
	@Column
	private String content1;
	
	@Column
	private String content2;
	
	@Column
	private String content3;
	
	@Column
	private String image1;
	
	@Column
	private String image2;
	
	@Column
	private String image3;
	
	@Column
	private String contents;
	
	@JoinColumn(name="location")
	@ManyToOne
	private Location location;
	
	@Column
	private LocalDateTime meetingdate;
	
	public void update(MeetingMakeDto dto, Hobby hobby, Location loc) {
		
		this.content1 = dto.getContent1();
		this.content2 = dto.getContent2();
		this.content3 = dto.getContent3();
		this.contents = dto.getContents();
		this.hobby = hobby;
		this.location = loc;
		this.title = dto.getTitle();
		this.image1 = dto.getImage1();
		this.image2 = dto.getImage2();
		this.image3 = dto.getImage3();
		this.status = dto.getStatus();
		this.meetingdate = dto.getMeetingtime().toLocalDateTime();
	}
	
	public void makePhoto(Deque<String> photo) {
		
		this.image1 = photo.pollFirst();
		this.image2 = photo.pollFirst();
		this.image3 = photo.pollFirst();
		
	}
	
}
