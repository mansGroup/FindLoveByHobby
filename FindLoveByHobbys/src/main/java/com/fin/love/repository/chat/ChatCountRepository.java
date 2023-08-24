package com.fin.love.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatCountRepository extends JpaRepository<ChatCount, Long> {

    List<ChatCount> findByMaleid(String myId);

    List<ChatCount> findByFemaleid(String myId);
}
