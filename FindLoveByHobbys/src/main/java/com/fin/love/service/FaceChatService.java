package com.fin.love.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fin.love.dto.facechat.MakeFaceChatRoomDto;

import com.fin.love.dto.facechat.ReportFaceChatDto;
import com.fin.love.repository.facechat.FaceChatRepository;
import com.fin.love.repository.facechat.FaceReportRepository;
import com.fin.love.repository.facechat.Speakchat;
import com.fin.love.repository.facechat.Speakroom;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FaceChatService {
	
	@Autowired
	private FaceChatRepository facerepository;
	
	@Autowired
	private FaceReportRepository facereport;
	
	@Autowired
	private MemberRepository memberrepository;
	
	// 채팅방 유저들의 이름을 불러오기.
	public List<Member> loadMemberName(MakeFaceChatRoomDto dto){
		
		log.info("loadMemberName = {}",dto.getSpeakmember1() + "/" + dto.getSpeakmember2());
		
		return memberrepository.findMyNameForFacechat(dto.getSpeakmember1(), dto.getSpeakmember2());
		
	}
	
	// 채팅 방 존재 유무 확인 후 생성, 신고된 방의 경우 열리지 않도록 Ban
	public int makeRoom(MakeFaceChatRoomDto dto) {
		
		Speakroom room = facerepository.findByRoomid(dto.toEntity().getRoomid());
		if(room==null) {
			Speakroom makeroom = dto.toEntity();
			makeroom.findNowTime();
			facerepository.save(makeroom);
			return 1;
		} else {
			
			List<Speakchat> report = facereport.findBySpeakroomRoomid(room.getRoomid());
			log.info("{}",report);
			if(report==null || report.size()==0) {
				
				return 1;
				
			} else {
				
				return 0;
				
			}
		}
		
		
		
	}
	
	// 화상채팅 방 신고
	public void doReport(ReportFaceChatDto dto) {
		
		Speakroom room = facerepository.findByRoomid(dto.getRoomId());
		log.info("{}",room);
		Speakchat report = dto.toEntity(room);
		log.info("report = {}",report);
		facereport.save(report);
		
	}
	
}
