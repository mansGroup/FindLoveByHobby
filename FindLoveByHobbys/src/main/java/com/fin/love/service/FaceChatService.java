package com.fin.love.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fin.love.dto.facechat.MakeFaceChatRoomDto;
import com.fin.love.dto.facechat.NickNameDto;
import com.fin.love.dto.facechat.ReportFaceChatDto;
import com.fin.love.repository.facechat.FaceChatRepository;
import com.fin.love.repository.facechat.FaceReportRepository;
import com.fin.love.repository.facechat.SpeakChat;
import com.fin.love.repository.facechat.SpeakRoom;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FaceChatService {
	
	@Autowired
	private FaceChatRepository facerepository;
	
	@Autowired
	private FaceReportRepository facereport;
	
	// 채팅방 유저들의 이름을 불러오기.
	public List<NickNameDto> loadMemberName(long roomid){
		
		log.info("loadMemberName = {}",roomid);
		
		return facerepository.findbySpeakroomJoinMember(roomid);
		
	}
	
	// 채팅 방 존재 유무 확인 후 생성, 신고된 방의 경우 열리지 않도록 Ban
	public int makeRoom(MakeFaceChatRoomDto dto) {
		
		SpeakRoom room = facerepository.findByRoomid(dto.toEntity().getRoomid());
		if(room==null) {
			SpeakRoom makeroom = dto.toEntity();
			makeroom.findNowTime();
			facerepository.save(makeroom);
			return 1;
		} else {
			
			SpeakChat report = facereport.findBySpeakRoom(room);
			if(report==null) {
				
				return 1;
				
			} else {
				
				return 0;
				
			}
		}
		
		
		
	}
	
	// 화상채팅 방 신고
	public void doReport(ReportFaceChatDto dto) {
		
		SpeakRoom room = facerepository.findByRoomid(dto.getRoomId());
		log.info("{}",room);
		SpeakChat report = dto.toEntity(room);
		log.info("report = {}",report);
		facereport.save(report);
		
	}
	
}
