package com.fin.love.web;

import com.fin.love.repository.chat.ChatCount;
import com.fin.love.service.chatting.ChatCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("downCount/{roomId}/{myId}/{mySex}")
    public void downChatCount(@PathVariable Long roomId,
                            @PathVariable String myId,
                            @PathVariable int mySex) {

        chatCountService.downChatCount(roomId, myId, mySex);
    }

    @GetMapping("/countList/{myId}/{mySex}")
    public ResponseEntity<List<ChatCount>> getChatCountList(
            @PathVariable String myId,
            @PathVariable int mySex) {

        List<ChatCount> chatCountList = chatCountService.getChatCountList(myId, mySex);

        return ResponseEntity.ok(chatCountList);
    }
}
