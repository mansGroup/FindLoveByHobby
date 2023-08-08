package com.fin.love.repository.meetingmember;

import java.time.LocalDateTime;

import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.meeting.Meeting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "MEETINGMEMBER")
@SequenceGenerator(name = "MEETINGMEMBER_SEQ_GEN", sequenceName = "MEETINGMEMBER_SEQ" ,allocationSize = 1)
public class MeetingMember {

	@Id
	@GeneratedValue(generator = "MEETINGMEMBER_SEQ_GEN")
	private long id;
	
	@JoinColumn(name = "meetid")
	@ManyToOne
	private Meeting meeting;
	
	@Column
	private String userid;
	
	@Column
	private int userage;
	
	
}
