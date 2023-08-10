package com.fin.love.web;

import com.fin.love.service.chatting.ChatCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatCount")
@RestController
public class ChatCountController {

    private final ChatCountService chatCountService;
    @GetMapping("upCount/{roomId}/{myId}/{mySex}")
    public void upChatCount(@PathVariable Long roomId,
                            @PathVariable String myId,
                            @PathVariable int mySex) {

        chatCountService.upChatCount(roomId, myId, mySex);
    }
}
