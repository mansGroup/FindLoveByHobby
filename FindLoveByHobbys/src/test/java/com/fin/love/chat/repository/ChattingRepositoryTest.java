package com.fin.love.chat.repository;

import com.fin.love.repository.chat.ChattingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ChattingRepositoryTest {

    @Autowired
    private ChattingRepository chattingRepository;


}
