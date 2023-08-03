package com.fin.love.chat.repository;

import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.repository.chat.ChattingRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Slf4j
@SpringBootTest
public class ChatRoomRepositoryTest {

    @Autowired
    private ChattingRoomRepository chattingRepository;

    @Test
    public void makeRoom() {
        ChattingRoom entity = ChattingRoom.builder()
                .myId("user1")
                .otherId("user2")
                .build();

        chattingRepository.saveAndFlush(entity);
    }

    @Test
    public void findByMyIdTest() {
        List<ChattingRoom> list = chattingRepository.findByMyId("user2");
        Assertions.assertNotNull(list);
        log.info(list.toString()+"========================================");
    }


}
