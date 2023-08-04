package com.fin.love.web;

import com.fin.love.repository.chat.Chatting;
import com.fin.love.service.chatting.ChattingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/api")
@RestController
public class ChattingRestController {

    @Autowired
    private ChattingService chattingService;

    @GetMapping("/chatting/{roomId}/{myNickname}/{message}")
    public ResponseEntity<String> saveChatting(
            @PathVariable Long roomId,
            @PathVariable String myNickname,
            @PathVariable String message
    ) {

        Chatting chatting = Chatting.builder()
                .contentid(roomId)
                .nickname(myNickname)
                .conversation(message)
                .build();

        chattingService.save(chatting);

        return ResponseEntity.ok("ok");
    }
}
