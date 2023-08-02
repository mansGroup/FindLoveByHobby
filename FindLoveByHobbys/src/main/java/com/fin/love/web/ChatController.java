package com.fin.love.web;

import com.fin.love.repository.chat.Chat;
import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.chatting.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatController {

    private final ChattingService chattingService;
    private final ChattingRoomService chattingRoomService;

    @GetMapping("/chat")
    public void chat(Model model) {
        log.info("chat()");

        // TODO spring security session 적용
        // 세션에서 id 찾아오기
        String session = "user1";

        // id로 채팅방 리스트 가져오기
        List<ChattingRoom> list = chattingRoomService.getChattingRoomListById(session);

        // 모델에 리스트 실어주기
        model.addAttribute("list", list);
    }
}


