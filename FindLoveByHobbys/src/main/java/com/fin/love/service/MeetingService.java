package com.fin.love.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fin.love.dto.meeting.MeetingMakeDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.location.LocationRepository;
import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.meeting.MeetingRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeetingService {

	@Autowired
	private MeetingRepository meetingrepository;

	@Autowired
	private HobbyRepository hobbyrepository;

	@Autowired
	private LocationRepository locrepository;
	
	@Autowired
	private AgeRepository agerepository;

	public List<Meeting> makelist(int num) {

		log.info("read All");

		List<Meeting> list = meetingrepository.findAll();

		int len = list.size();
		int start = num - 1 >= 0 ? num - 1 : 0;
		int startcheck = start * 6 > len ? (len / 6) * 6 : start * 6;
		List<Meeting> list2 = new ArrayList<>();
		if (len > startcheck && len >= num * 6) {

			for (int i = start * 6; i < num * 6; i++) {

				list2.add(list.get(i));

			}

		} else if (len < startcheck) {

			for (int i = startcheck; i < len; i++) {

				list2.add(list.get(i));

			}

		} else if (len > startcheck && len < num * 6) {

			for (int i = startcheck; i < len; i++) {

				list2.add(list.get(i));

			}

		}

		return list2;

	}

	public void create(MeetingMakeDto dto) {

		Hobby hobby = hobbyrepository.findById(dto.getHobbyid()).orElseThrow();
		Location loc = locrepository.findById(dto.getLocationid()).orElseThrow();
		log.info("create(dto = {}, hobby = {}, loc = {})", dto, hobby, loc);
		Meeting meeting = dto.toEntity(loc, hobby);

		meetingrepository.save(meeting);

	}

	public void update(MeetingMakeDto dto, long id) {

		Hobby hobby = hobbyrepository.findById(dto.getHobbyid()).orElseThrow();
		Location loc = locrepository.findById(dto.getLocationid()).orElseThrow();
		log.info("create(dto = {}, hobby = {}, loc = {})", dto, hobby, loc);
		Meeting meeting = meetingrepository.findById(id).orElseThrow();
		meeting.update(dto, hobby, loc);

		meetingrepository.save(meeting);
	}

	public int delete(long id) {
		Meeting meet;

		meet = meetingrepository.findById(id).orElseThrow();

		if (meet.getStatus() != 0) {

			return 0;

		}

		meetingrepository.delete(meet);
		return 1;

	}

	public List<Hobby> loadhobby() {
		
		return hobbyrepository.findAll();
		
	}

	public List<Location> loadloc() {

		return locrepository.findAll();
		
	}
	
	public List<Age> loadage(){
		
		return agerepository.findAll();
		
	}

}
