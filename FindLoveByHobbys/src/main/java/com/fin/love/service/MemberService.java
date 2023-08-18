package com.fin.love.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fin.love.dto.member.UpdateInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.member.MemberLogInDto;
import com.fin.love.dto.member.MemberSignUpDto;
import com.fin.love.repository.profile.Height;
import com.fin.love.repository.profile.HeightRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import com.fin.love.respository.member.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

	public String signUp(MemberSignUpDto dto) {
		log.info("signUp(dto={})", dto);

		Member entity = Member.builder().id(dto.getUserid()).name(dto.getUsername())
				.password(passwordEncoder.encode(dto.getPassword()))
				.email(dto.getEmail())
				.nickname(dto.getNickname())
				.role(dto.getRole()).sex(dto.getSex()).phone(dto.getPhone()).address(dto.getAddress()).birthday(dto.getBirthdate()).build();
		log.info("save 전: entity={}", entity);
		memberRepository.save(entity);
		log.info("save 후: entity={}", entity);

		return entity.getId();

	}

	public boolean idDupleCheck(String userid) {
		log.info("idDupleCheck(userid={})", userid);
		boolean result = memberRepository.existsById(userid);
		log.info("result=({})", result);
		return result;
	}

	public Boolean nickDupleCheck(String nickname) {
		log.info("nickDupleCheck(nickname={})", nickname);

		int result = memberRepository.countByNickname(nickname);
		log.info("result=({})", result);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		log.info("loadUserByUsername(id={})", id);

		Member user = memberRepository.findById(id).orElseThrow();

		if (user != null) {
			log.info("user(user={})", user);
			return user; // 로그인 성공한 경우 UserDetails 객체 반환
		}
		log.info("logInFail");
		throw new UsernameNotFoundException(id + "가 존재하지 않습니다.");
	}

	public String getNicknameById(String id) {

		Member member = memberRepository.findById(id).orElseThrow();

		return member.getNickname();
	}

	public Member getSexById(String session) {
		return memberRepository.findById(session).orElseThrow();
	}

	public Member login(String username) {
	
		return memberRepository.findById(username).orElseThrow();
	}
	
	@Transactional
	public void updateRole(String userId) {
		log.info("updateRole(userId = {})", userId);
		
		Member member = memberRepository.findById(userId).orElseThrow();
		log.info("member >>> " + member);
		
		member.updateRole(Role.USER);
	}


    public Member getMemberInfo(String id) {
		return memberRepository.findById(id).orElseThrow();
    }

	public Member getMemberOrElseEmptyEntity(String id) {
		Member entity = memberRepository.findById(id).orElseGet(Member::new);
		return entity;
	}

	public List<Member> getMemberInfoByUsername(String username) {
		return  memberRepository.findByName(username);
	}

	@Transactional
	public void updatePassword(String userId, String temporaryPassword) {
		Member entity = memberRepository.findById(userId).orElseThrow();
		String EncryptedPassword = passwordEncoder.encode(temporaryPassword);
		entity.updatePassword(EncryptedPassword);
	}

	
	
	// 멤버 리스트 불러오기 (Profile)
	@Transactional(readOnly = true)
	public List<Member> readMembefrList() {
		log.info("readMembefrList()");
		
		return memberRepository.findAll();
	}


	@Transactional
    public UpdateInfoDto updateUserInfo(UpdateInfoDto dto) {
		Member entity = memberRepository.findById(dto.getId()).orElseThrow();
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity.updateInfo(dto);
		return UpdateInfoDto.builder()
				.id(entity.getId())
				.password("")
				.email(entity.getEmail())
				.address(entity.getAddress())
				.phone(entity.getPhone())
				.build();
    }
}
