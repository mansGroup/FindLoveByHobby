package com.fin.love.repository.facechat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fin.love.dto.facechat.NickNameDto;

import java.util.List;


@Repository
public interface FaceChatRepository extends JpaRepository<SpeakRoom, Long>{

	SpeakRoom findByRoomid(long roomid);
	
	@Query("SELECT m.id, m.name FROM com.fin.love.respository.member.Member m JOIN SpeakRoom s ON s.speakmember1 = m.id OR s.speakMember2 = m.id WHERE s.roomid = :roomid")
	List<NickNameDto> findbySpeakroomJoinMember(@Param("roomid") long roomid);
}
