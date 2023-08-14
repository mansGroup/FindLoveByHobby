package com.fin.love.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {

    List<ChattingRoom> findByMaleid(String userId);

    List<ChattingRoom> findByFemaleid(String userId);
}
