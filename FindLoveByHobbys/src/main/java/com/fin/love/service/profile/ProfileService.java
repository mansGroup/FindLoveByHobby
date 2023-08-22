package com.fin.love.service.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.profile.ProfileCreateDto;
import com.fin.love.dto.profile.ProfileSearchDto;
import com.fin.love.dto.profile.ProfileUpdateDto;
import com.fin.love.repository.image.PictureRepository;
import com.fin.love.repository.profile.Age;
import com.fin.love.repository.profile.AgeRepository;
import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.HeightRepository;
import com.fin.love.repository.profile.Profile;
import com.fin.love.repository.profile.ProfileRepository;
import com.fin.love.repository.profile.UserHobby;
import com.fin.love.repository.profile.UserHobbyRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;
import com.fin.love.service.PictureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

	private final MemberRepository memberRepository;
	private final ProfileRepository profileRepository;
	private final UserHobbyRepository userHobbyRepository;
	private final AgeRepository ageRepository;
	private final HeightRepository heightRepository;
	private final PictureRepository pictureRepository;
	private final PictureService pictureService;

	public void createProfile(ProfileCreateDto dto) {
		log.info("createProfile(dto= {})", dto);

		Profile entity = dto.toEntity();
		log.info("entity= {}", entity);

		entity = profileRepository.save(entity);
		log.info("pro= {}", entity);
	}

	@Transactional(readOnly = true)
	public Profile profileModify(String userId) {
		log.info("profileModify(userId={})", userId);

		return profileRepository.findById(userId).orElseThrow();
	}

	@Transactional
	public void profileUpdate(ProfileUpdateDto dto) {
		log.info("profileUpdate(dto={})", dto);

		Profile entity = profileRepository.findById(dto.getUserId()).orElseThrow();
		entity.update(dto);
	}

	@Transactional
	public void profileDelete(String userId) {
		log.info("profileDelete(userId={})", userId);

		profileRepository.deleteById(userId);
	}

	@Transactional(readOnly = true)
	public Profile findById(String userid) {
		log.info("findById(userId={})", userid);

		return profileRepository.findById(userid).orElse(null);
	}

	public List<Member> findByhobbys(Long hobbyId1, Long hobbyId2, Long hobbyId3, String userId) {
		log.info("findByhobbys(hobbyId1 = {}, hobbyId2 = {}, hobbyId3 = {})", hobbyId1, hobbyId2, hobbyId3);
		List<Member> members = new ArrayList<>();
		Member loginUser = memberRepository.findById(userId).orElseThrow();
		Set<String> memberIds = new HashSet<>();

		if (hobbyId1 != null) {
			List<UserHobby> userHobbys = userHobbyRepository.findByHobbyId(hobbyId1);
			for (int i = 0; i < userHobbys.size(); i++) {
				memberIds.add(userHobbys.get(i).getUserid());
			}
		}

		if (hobbyId2 != null) {
			List<UserHobby> userHobbys = userHobbyRepository.findByHobbyId(hobbyId2);
			for (int i = 0; i < userHobbys.size(); i++) {
				memberIds.add(userHobbys.get(i).getUserid());
			}
		}

		if (hobbyId3 != null) {
			List<UserHobby> userHobbys = userHobbyRepository.findByHobbyId(hobbyId3);
			for (int i = 0; i < userHobbys.size(); i++) {
				memberIds.add(userHobbys.get(i).getUserid());
			}
		}

		List<String> idList = new ArrayList<>(memberIds);

		for (int i = 0; i < idList.size(); i++) {
			Member member = memberRepository.findById(idList.get(i)).orElseThrow();
			if (member.getSex() != loginUser.getSex()) {
				members.add(member);
			}
		}

		return members;
	}

	public List<Member> membersByage(List<Member> members, int age) {
		log.info("membersByage(age = {})", age);
		List<Member> list = new LinkedList<>();
		int minage = 0;
		int maxage = 0;

		if (age >= 6) {
			minage = age - 5;
		} else {
			minage = 1;
		}

		if (age <= 26) {
			maxage = age + 5;
		} else {
			maxage = 31;
		}

		for (int i = 0; i < members.size(); i++) {
			Profile memberPro = profileRepository.findById(members.get(i).getId()).orElseThrow();

			if (memberPro.getUserAge() >= minage && memberPro.getUserAge() <= maxage) {
				list.add(members.get(i));
			}

		}
		
		if (list.size() == 0) {
			return members;
		}

		return list;
	}

	public List<Member> membersByHeight(List<Member> members, int height) {
		log.info("membersByHeight(height = {})", height);
		List<Member> list = new LinkedList<>();
		int minHe = 0;
		int maxHe = 0;

		if (height >= 6) {
			minHe = height - 5;
		} else {
			minHe = 1;
		}

		if (height <= 76) {
			maxHe = height + 5;
		} else {
			maxHe = 81;
		}

		for (int i = 0; i < members.size(); i++) {

			Profile memberPro = profileRepository.findById(members.get(i).getId()).orElseThrow();

			if (memberPro.getUserHeight() >= minHe && memberPro.getUserHeight() <= maxHe) {
				list.add(members.get(i));
			}

		}
		
		if (list.size() == 0) {
			return members;
		}

		return list;
	}

	public List<ProfileSearchDto> toProfileSearchDto(List<Member> members) {
		log.info("toProfileSearchDto()");
		List<ProfileSearchDto> dtos = new LinkedList<>();
		List<Age> ages = ageRepository.findAll();
		List<Height> heights = heightRepository.findAll();
		
		for (int i = 0; i < members.size(); i++) {
			Profile pro = profileRepository.findById(members.get(i).getId()).orElseThrow();
			
			String age = ages.get(pro.getUserAge() - 1).getAgeName();
			String height = heights.get(pro.getUserHeight() - 1).getHeightName();
			String pic = pictureRepository.findById(members.get(i).getId()).orElseThrow().getPic1();
			pic = pictureService.imageChange(pic);
			
			ProfileSearchDto dto = new ProfileSearchDto(members.get(i).getId(), members.get(i).getName(), age, height, pro.getUserIntroduce(), pic);
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	public List<ProfileSearchDto> dtoSort(List<ProfileSearchDto> dto, String userId) {
		log.info("dtoSort()");
		
		Role role = Role.USER;
		int userGender = memberRepository.findById(userId).orElseThrow().getSex();
		String member1Id = "";
		String member2Id = "";
		String member3Id = "";
		String member4Id = "";
		
		if (dto.size() < 4) {
			List<ProfileSearchDto> result = dto;
			
			if (dto.size() == 1) {
				member1Id = dto.get(0).getUserId();
			} else if (dto.size() == 2) {
				member1Id = dto.get(0).getUserId();
				member2Id = dto.get(1).getUserId();
			} else if (dto.size() == 3) {
				member1Id = dto.get(0).getUserId();
				member2Id = dto.get(1).getUserId();
				member3Id = dto.get(2).getUserId();
			} else if (dto.size() == 4) {
				member1Id = dto.get(0).getUserId();
				member2Id = dto.get(1).getUserId();
				member3Id = dto.get(2).getUserId();
				member4Id = dto.get(3).getUserId();
			}
			
			List<Member> members = memberRepository.findAll();
			
			List<Member> sortMembers = new ArrayList<>();
			
			for (int i = 0; i < members.size(); i++) {
				if (userGender != members.get(i).getSex() 
						 && !members.get(i).getId().equals(member1Id)
						 && !members.get(i).getId().equals(member2Id) 
						 && !members.get(i).getId().equals(member3Id) 
						 && !members.get(i).getId().equals(member4Id)
						 && members.get(i).getRole() == role) {
					
					sortMembers.add(members.get(i));
					
				}
			}
			
			List<ProfileSearchDto> memberDtos = toProfileSearchDto(sortMembers);
			
			int number1 = -1;
			int number2 = -1;
			int number3 = -1;
			int number4 = -1;
			int size = dto.size();
			
			for (; ;) {
				
				Random ran = new Random();

				int randomInt = ran.nextInt(memberDtos.size());
				
				if (number1 != randomInt && number2 != randomInt && number3 != randomInt && number4 != randomInt) {
					number4 = number3;
					number3 = number2;
					number2 = number1;
					number1 = randomInt;
					result.add(memberDtos.get(randomInt));
					
					size++;
					
					if (size == 4) {
						break;
					}
					
					
				}
				
			}
			
			return result;
		}
		
		List<ProfileSearchDto> result = new ArrayList<>();
		Random ran = new Random();

		int number1 = 0;
		int number2 = 0;
		int number3 = 0;
		int number4 = 0;

		for (int i = 0; i < 4; i++) {
			int randomInt = ran.nextInt(dto.size());

			if (number1 != randomInt && number2 != randomInt && number3 != randomInt && number4 != randomInt) {
				number4 = number3;
				number3 = number2;
				number2 = number1;
				number1 = randomInt;
				result.add(dto.get(randomInt));
			} else {
				i--;
			}
		}
		
		return result;
	}

	public List<Member> findByAll() {
		log.info("findByAll()");
		
		return memberRepository.findAll();
	}

	public List<Member> findByRole(List<Member> members) {
		log.info("findByRole()");
		
		List<Member> result = new ArrayList<>();
		Role role = Role.USER;
		
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getRole() == role) {
				result.add(members.get(i));
			}
		}
		
		return result;
	}

	public List<Member> findByGender(List<Member> members, int userGender) {
		log.info("findByGender");
		List<Member> result = new ArrayList<>();
		
		for (int i = 0; i < members.size(); i++) {
			
			if (userGender != members.get(i).getSex()) {
				result.add(members.get(i));
			}
			
		}
		
		return result;
	}

//	

}
