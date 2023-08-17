package com.fin.love.service;

import java.io.ByteArrayOutputStream;
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
import com.fin.love.repository.image.Picture;
import com.fin.love.repository.image.PictureRepository;
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
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Slf4j
public class MeetingService {

	@Autowired
	private MemberRepository memberrepository;

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

	@Autowired
	private PictureRepository picrepository;

	@Autowired
	private PictureService picservice;

	public List<Meeting> myLeaderList(String userid, int status) {

		List<Meeting> list = meetingrepository.findByLeader(userid);
		List<Meeting> list2 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {

			Meeting met = list.get(i);

			if (met.getStatus() != status) {

				continue;

			}

			String[] deq = new String[] { imageToBase64(met.getImage1()), imageToBase64(met.getImage2()),
					imageToBase64(met.getImage3()) };

			met.makePhoto(deq);

			list2.add(met);

		}

		return list2;
	}

	public List<Meeting> myMeetList(String userid, int status) {

		List<Meeting> list = meetingrepository.findAll();
		List<Meeting> list2 = new ArrayList<>();
		for (Meeting x : list) {

			if (userid.equals(x.getLeader()) || x.getMember() == 1 || x.getStatus() != status) {

				continue;

			}
			List<MeetingMember> list3 = mtmemrepository.findByMeetingId(x.getId());
			for (MeetingMember y : list3) {

				if (userid.equals(y.getProfile().getUserId())) {

					Meeting met = x;

					String[] deq = new String[] { imageToBase64(met.getImage1()), imageToBase64(met.getImage2()),
							imageToBase64(met.getImage3()) };

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

		List<Meeting> lists = meetingrepository.findAll();
		List<Meeting> list = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {

			if (lists.get(i).getStatus() == 1) {

				continue;

			}

			list.add(lists.get(i));

		}
		log.info("{}", list);

		int len = list.size();
		int start = num - 1 >= 0 ? num - 1 : 0;
		int end = num * 3 >= list.size() ? list.size() : num * 3;

		List<Meeting> list2 = new ArrayList<>();

		for (int i = start * 3; i < end; i++) {

			Meeting met = list.get(i);

			String[] deq = new String[] { imageToBase64(met.getImage1()), imageToBase64(met.getImage2()),
					imageToBase64(met.getImage3()) };

			met.makePhoto(deq);

			list2.add(met);

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
		log.info("update(dto = {}, hobby = {}, loc = {})", dto, hobby, loc);
		Meeting meeting = meetingrepository.findById(id).orElseThrow();
		meeting.update(dto, hobby, loc);

		meetingrepository.save(meeting);
	}

	public int delete(long id) {
		Meeting meet;

		meet = meetingrepository.findById(id).orElseThrow();
		mtmemrepository.deleteAllByMeetingId(meet.getId());
		if (meet.getStatus() != 0) {

			return 0;

		}

		meetingrepository.deleteById(id);
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
		log.info("imgpt = {}", imagePath);

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

		List<Meeting> list2 = meetingrepository.findByStatus(0);

		if (list2 == null || list2.size() == 0) {

			return new ArrayList<Meeting>();

		}

		List<Meeting> listh = findByHobbyAndLocation(list2, dto);
		List<Meeting> list = findByAge(listh, dto.getAgeId());
		List<Meeting> lists = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			String[] deq = new String[3];
			Meeting meet = list.get(i);
			log.info("image = {}", meet.getImage1());
			if (meet.getImage1() != null) {
				log.info("이미지 {}", meet.getImage1());
				String img = imageToBase64(meet.getImage1());
				deq[0] = img;
				log.info("이미지 {}", img);

			}

			if (meet.getImage2() != null) {
				String img = imageToBase64(meet.getImage2());
				deq[1] = img;
				log.info("이미지 {}", img);
			}

			if (meet.getImage3() != null) {
				String img = imageToBase64(meet.getImage3());
				deq[2] = img;
				log.info("이미지 {}", img);
			}

			meet.makePhoto(deq);
			lists.add(meet);
		}

		return lists;
	}

	public List<Meeting> findByHobbyAndLocation(List<Meeting> unlist, MeetingSearchDto dto) {
		List<Meeting> list = new ArrayList<>();

		for (Meeting x : unlist) {

			if (x.getHobby().getHobbyId() == dto.getHobbyId() && x.getLocation().getId() == dto.getLocationId()) {

				list.add(x);

			}

		}

		return list;
	}

	public List<Meeting> findByAge(List<Meeting> unlist, long ageId) {
		List<Meeting> list = new ArrayList<>();

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
			if (age == 0) {

				return list;

			}

			age = ((age / list2.size()) / 10) * 10;

			if (age - 10 <= ageId && age + 10 >= ageId) {

				list.add(x);
				count++;
			}

		}

		return list;
	}

	@Transactional(readOnly = true)
	public MeetingModifyDto readMyMeeting(long id) {

		Meeting meet = meetingrepository.findById(id).orElseThrow();
		log.info("meet = {}", meet);
		MeetingModifyDto dto = MeetingModifyDto.builder().build();

		dto.imgMake(meet);

		String img1 = imageToBase64(meet.getImage1());
		String img2 = imageToBase64(meet.getImage2());
		String img3 = imageToBase64(meet.getImage3());

		String[] s1 = new String[] { img1, img2, img3 };

		meet.makePhoto(s1);

		dto.entityMake(meet);

		return dto;

	}

	public List<List<MeetingMember>> readMyMember(long id) {
		// TODO Auto-generated method stub
		List<MeetingMember> list = mtmemrepository.findByMeetingId(id);
		List<MeetingMember> woman = new ArrayList<>();
		List<MeetingMember> man = new ArrayList<>();
		List<List<MeetingMember>> list2 = new ArrayList<>();
		for (MeetingMember x : list) {

			String userid = x.getProfile().getUserId();
			Member member = memberrepository.findById(userid).orElseThrow();

			if (member.getSex() == 2) {

				woman.add(x);

			} else {

				man.add(x);

			}

		}
		list2.add(woman);
		list2.add(man);

		log.info("list2 = {}", list2.get(0));
		log.info("list2 = {}", list2.get(1));

		return list2;

	}

	public List<String> imagePrint(List<MeetingMember> list, int gender) throws Exception {

		List<String> img = new ArrayList<>();

		for (MeetingMember x : list) {

			if (gender == 2) {

				img.add(imageToBase64("C:\\IMA\\neo.gif"));

			} else {

				img.add(imageToBase64("C:\\IMA\\prodo.gif"));

			}

		}

		return img;

	}

	public void updateAddMember(long id, String userid) {
		// TODO Auto-generated method stub

		Meeting meet = meetingrepository.findById(id).orElseThrow();

		List<MeetingMember> lists = mtmemrepository.findByMeetingId(id);

		int mans = 0;
		int womans = 0;
		Member mem2 = memberrepository.findById(userid).orElseThrow();
		for (MeetingMember x : lists) {

			Member mem = memberrepository.findById(x.getProfile().getUserId()).orElseThrow();

			if (mem.getSex() == 1) {

				mans++;

			} else {

				womans++;

			}

		}

		int manlimit = meet.getMalecount();
		int womanlimit = meet.getFemalecount();

		if (mem2.getSex() == 1) {

			if (manlimit - mans <= 0) {

				return;

			}

		} else {

			if (womanlimit - womans <= 0) {

				return;

			}

		}

		Profile profile = profilerepository.findById(userid).orElseThrow();

		MeetingMember meetmem = MeetingMember.builder().meeting(meet).profile(profile).build();

		mtmemrepository.save(meetmem);

	}

	public void updateRemove(long id, String userid) {
		Meeting meet = meetingrepository.findById(id).orElseThrow();

		Profile profile = profilerepository.findById(userid).orElseThrow();

		List<MeetingMember> meetmem = mtmemrepository.findByMeetingId(id);
		MeetingMember delmem = null;
		for (MeetingMember x : meetmem) {

			if (userid.equals(x.getProfile().getUserId())) {

				delmem = x;
				break;

			}

		}

		mtmemrepository.deleteById(delmem.getId());

	}

	public int checkInvited(List<List<MeetingMember>> list, String userid) {

		for (List<MeetingMember> x : list) {

			for (MeetingMember y : x) {

				if (userid.equals(y.getProfile().getUserId())) {

					return 1;

				}

			}

		}

		return 0;
	}

	public List<Meeting> findAll() {
		// TODO Auto-generated method stub
		return meetingrepository.findAll();
	}

	public Meeting readById(long id) {

		return meetingrepository.findById(id).orElseThrow();

	}

	public List<Meeting> findAllByStatus(int status) {
		// TODO Auto-generated method stub
		List<Meeting> list = meetingrepository.findByStatus(status);

		return list;
	}
}
