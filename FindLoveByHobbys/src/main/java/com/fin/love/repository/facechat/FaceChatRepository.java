package com.fin.love.repository.facechat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.fin.love.respository.member.Member;

import java.util.List;


@Repository
public interface FaceChatRepository extends JpaRepository<Speakroom, Long>{

	Speakroom findByRoomid(long roomid);
	
	
}
