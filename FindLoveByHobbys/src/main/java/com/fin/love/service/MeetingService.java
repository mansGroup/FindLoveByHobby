package com.fin.love.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.meeting.MeetingMakeDto;
import com.fin.love.dto.meeting.MeetingModifyDto;
import com.fin.love.dto.meeting.MeetingSearchDto;
import com.fin.love.repository.hobby.Hobby;
import com.fin.love.repository.hobby.HobbyRepository;
import com.fin.love.repository.location.Location;
import com.fin.love.repository.location.LocationRepository;
import com.fin.love.repository.meeting.Meeting;
import com.fin.love.repository.meeting.MeetingRepository;
import com.fin.love.repository.meetingmember.MeetingMember;
import com.fin.love.repository.meetingmember.MeetingMemberRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;


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

	@Autowired
	private MeetingMemberRepository mtmemrepository;

	@Autowired
	private ProfileRepository profilerepository;
	
	public List<Meeting> myLeaderList(String userid){
		
		List<Meeting> list = meetingrepository.findByLeader(userid);
		List<Meeting> list2 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			
			Meeting met = list.get(i);

			String[] deq = new String[] {imageToBase64(met.getImage1()),imageToBase64(met.getImage2()),imageToBase64(met.getImage3())};

			met.makePhoto(deq);

			list2.add(met);

		}
		
		
		return list2;
	}
	
	public List<Meeting> myMeetList(String userid){
		
		List<Meeting> list = meetingrepository.findAll();
		List<Meeting> list2 = new ArrayList<>();
		for(Meeting x : list) {
			
			if(userid.equals(x.getLeader()) || x.getMember() == 1) {
				
				continue;
				
			}
			
			for(MeetingMember y : x.getMembers()) {
				
				if(userid.equals(y.getProfile().getUserId())) {
					
					Meeting met = x;

					String[] deq = new String[] {imageToBase64(met.getImage1()),imageToBase64(met.getImage2()),imageToBase64(met.getImage3())};

					met.makePhoto(deq);

					list2.add(met);
					
					break;
					
				}
				
			}
			
		}
		return list2;
		
	}
	
	
	
	
	public List<Meeting> makelist(int num) {

		log.info("read All");

		List<Meeting> list = meetingrepository.findAll();

		int len = list.size();
		int start = num - 1 >= 0 ? num - 1 : 0;
		int startcheck = start * 6 > len ? (len / 6) * 6 : start * 6;
		List<Meeting> list2 = new ArrayList<>();
		if (len > startcheck && len >= num * 6) {

			for (int i = start * 6; i < num * 6; i++) {
				
				Meeting met = list.get(i);

				String[] deq = new String[] {imageToBase64(met.getImage1()),imageToBase64(met.getImage2()),imageToBase64(met.getImage3())};

				met.makePhoto(deq);

				list2.add(met);

			}

		} else if (len < startcheck) {

			for (int i = startcheck; i < len; i++) {

				
				Meeting met = list.get(i);
				
				String[] deq = new String[] {imageToBase64(met.getImage1()),imageToBase64(met.getImage2()),imageToBase64(met.getImage3())};
				
				

				met.makePhoto(deq);

				list2.add(met);

			}

		} else if (len > startcheck && len < num * 6) {

			for (int i = startcheck; i < len; i++) {

				Meeting met = list.get(i);

				String[] deq = new String[] {imageToBase64(met.getImage1()),imageToBase64(met.getImage2()),imageToBase64(met.getImage3())};
				met.makePhoto(deq);

				list2.add(met);

			}

		}

		return list2;

	}

	public void create(MeetingMakeDto dto) {

		Hobby hobby = hobbyrepository.findById(dto.getHobbyid()).orElseThrow();
		Location loc = locrepository.findById(dto.getLocationid()).orElseThrow();
		log.info("create(dto = {}, hobby = {}, loc = {})", dto, hobby, loc);
		Meeting meeting = dto.toEntity(loc, hobby);
		
		Meeting meet = meetingrepository.save(meeting);
		Profile profile = profilerepository.findById(dto.getLeader()).orElseThrow();
		
		mtmemrepository.save(MeetingMember.builder().profile(profile).meeting(meet).build());
		
		
		

	}

	public void update(MeetingMakeDto dto, long id) {

		Hobby hobby = hobbyrepository.findById(dto.getHobbyid()).orElseThrow();
		Location loc = locrepository.findById(dto.getLocationid()).orElseThrow();
		log.info("create(dto = {}, hobby = {}, loc = {})", dto, hobby, loc);
		Meeting meeting = meetingrepository.findById(id).orElseThrow();
		meeting.update(dto, hobby, loc, 1);

		meetingrepository.save(meeting);
	}

	public int delete(long id) {
		Meeting meet;

		meet = meetingrepository.findById(id).orElseThrow();
		mtmemrepository.deleteByMeetingId(meet.getId());
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

	public List<Age> loadage() {

		return agerepository.findAll();

	}

	public String imageToBase64(String imagePath) {
		log.info(imagePath);

		try {
			Path path = Paths.get(imagePath);
			byte[] imageBytes = Files.readAllBytes(path);
			return Base64.getEncoder().encodeToString(imageBytes);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	public List<Meeting> search(MeetingSearchDto dto) throws Exception {

		List<Meeting> list2 = meetingrepository.findAll();

		if (list2 == null) {

			return new ArrayList<Meeting>();

		}

		List<Meeting> listh = findByHobby(list2, dto.getHobbyId());
		List<Meeting> listl = findbyLocation(listh, dto.getLocationId());
		List<Meeting> lista = findByAge(listl, dto.getAgeId());
		List<Meeting> list = new ArrayList<>();
		Random random = new Random(42);
		int end = lista.size() >= 6 ? 6 : lista.size();
		List<Integer> nums = new ArrayList<>();

		for (int i = 0; i < end;) {
			int index = random.nextInt(0, lista.size());
			boolean check = false;
			for (int j = 0; j < nums.size(); j++) {

				if (nums.get(j) == index) {
					check = true;
					break;

				}

			}
			if (check == true) {

				continue;

			}

			list.add(lista.get(index));
			nums.add(index);
			i++;

		}

		for (int i = 0; i < list.size(); i++) {
			String[] deq = new String[3];
			log.info("image = {}", list.get(i).getImage1());
			if (list.get(i).getImage1() != null) {
				log.info("이미지 {}", list.get(i).getImage1());
				deq[0] = imageToBase64(list.get(i).getImage1());

			}

			if (list.get(i).getImage2() != null) {

				deq[1] = imageToBase64(list.get(i).getImage2());

			}

			if (list.get(i).getImage3() != null) {

				deq[2] = imageToBase64(list.get(i).getImage3());

			}
			list.get(i).makePhoto(deq);
		}

		return list;
	}

	public List<Meeting> findByHobby(List<Meeting> unlist, long hobbyId) {
		List<Meeting> list = new ArrayList<>();

		for (Meeting x : unlist) {

			if (x.getHobby().getHobbyId() == hobbyId) {

				list.add(x);

			}

		}

		return list;
	}

	public List<Meeting> findbyLocation(List<Meeting> unlist, long locationId) {

		List<Meeting> list = new ArrayList<>();

		for (Meeting x : unlist) {

			if (x.getLocation().getId() == locationId) {

				list.add(x);

			}

		}

		return list;
	}

	public List<Meeting> findByAge(List<Meeting> unlist, long ageId) {
		List<Meeting> list = new ArrayList<>();

		if (unlist.size() == 0 || unlist == null) {

			return list;

		}

		int count = 0;
		for (Meeting x : unlist) {
			if (count > 5) {

				break;

			}
			List<MeetingMember> list2 = mtmemrepository.findByMeetingId(x.getId());
			long age = 0;
			for (MeetingMember y : list2) {

				age += ((y.getProfile().getUserAge()) + 19);

			}
			if(age==0) {
				
				return list;
				
			}
			
			age = ((age / list2.size()) / 10) * 10;

			if (age == ageId) {

				list.add(x);
				count++;
			}

		}
		
		

		// 만약 원하는 나이대의 이성이 부족한 경우 비슷한 연령대 추가.
		if (count < 6) {

			for (Meeting x : unlist) {
				List<MeetingMember> list2 = mtmemrepository.findByMeetingId(x.getId());
				long age = 0;
				for (MeetingMember y : list2) {

					age += ((y.getProfile().getUserAge()) + 19);

				}
				if(age==0) {
					
					return list;
					
				}
				
				age = ((age / list2.size()) / 10) * 10;
				if (age != ageId && (age == ageId + 10 || age == ageId - 10)) {

					list.add(x);

				}

			}

		}

		return list;
	}

	@Transactional(readOnly = true)
	public MeetingModifyDto readMyMeeting(long id) {
		

		Meeting meet = meetingrepository.findById(id).orElseThrow();
		log.info("meet = {}",meet);
		MeetingModifyDto dto = MeetingModifyDto.builder().build();
		
		dto.imgMake(meet);
		
		String img1 = imageToBase64(meet.getImage1());
		String img2 = imageToBase64(meet.getImage2());
		String img3 = imageToBase64(meet.getImage3());
		
		String[] s1 = new String[] {img1, img2, img3};
		
		
		
		meet.makePhoto(s1);
		
		dto.entityMake(meet);
		
		return dto;
		
	}

}
