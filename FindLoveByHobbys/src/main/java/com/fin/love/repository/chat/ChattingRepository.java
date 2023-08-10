package com.fin.love.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {

    List<Chatting> findByContentidOrderByChatid(Long contentid);

    void deleteByContentid(Long contentid);
}
