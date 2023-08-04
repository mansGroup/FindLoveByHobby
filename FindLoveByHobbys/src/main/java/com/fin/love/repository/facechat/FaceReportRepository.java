package com.fin.love.repository.facechat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceReportRepository extends JpaRepository<Speakchat, Long>{

	List<Speakchat> findBySpeakroomRoomid(long roomid);
	
}
