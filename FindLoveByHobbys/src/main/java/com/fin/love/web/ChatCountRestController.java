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
public class ChatCountRestController {

    private final ChatCountService chatCountService;
    @GetMapping("upCount/{roomId}/{maleId}/{femaleId}/{mySex}")
    public void upChatCount(@PathVariable Long roomId,
                            @PathVariable String maleId,
                            @PathVariable String femaleId,
                            @PathVariable int mySex) {

        chatCountService.upChatCount(roomId, maleId, femaleId, mySex);
    }

    @GetMapping("downCount/{roomId}/{mySex}")
    public void downChatCount(@PathVariable Long roomId,
                            @PathVariable int mySex) {

        chatCountService.downChatCount(roomId, mySex);
    }

    @GetMapping("/countList/{myId}/{mySex}")
    public ResponseEntity<List<ChatCount>> getChatCountList(
            @PathVariable String myId,
            @PathVariable int mySex) {

        List<ChatCount> chatCountList = chatCountService.getChatCountList(myId, mySex);

        return ResponseEntity.ok(chatCountList);
    }
}
