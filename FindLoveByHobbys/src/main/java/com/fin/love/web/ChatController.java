package com.fin.love.web;

import com.fin.love.dto.chatting.ChattingListDto;
import com.fin.love.repository.chat.Chatting;
import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.service.MemberService;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.chatting.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatController {

    private final ChattingService chattingService;
    private final ChattingRoomService chattingRoomService;
    private final MemberService memberService;

    @GetMapping("/chat")
    public void chat(Model model) {
        log.info("chat()");

        // TODO spring security session 적용
        // 세션에서 id 찾아오기
        String session = "user2";

        // id로 채팅방 정보 가져오기
        List<ChattingRoom> idList = chattingRoomService.getChattingRoomListById(session);
        log.info(idList.toString());

        // 채팅방 정보에서 otherId 가져오기, contentid 가져오기
        List<ChattingListDto> dtoList = new ArrayList<>();
        ArrayList<String> otherIdList = new ArrayList<>();
        for (ChattingRoom chattingRoom : idList) {
            otherIdList.add(chattingRoom.getOtherId());
            ChattingListDto dto = new ChattingListDto(chattingRoom.getContentid());
            dtoList.add(dto);
        }
        log.info(otherIdList.toString());

        // 상대방 id 리스트로 닉네임 리스트 가져오기
        List<String> nicknameList = memberService.getNicknameById(otherIdList);
        log.info(nicknameList.toString());
        for (int i = 0; i < dtoList.size(); i++) {
            dtoList.get(i).setNickname(nicknameList.get(i));
        }
        // 닉네임과 content
        // 모델에 리스트 실어주기
        model.addAttribute("list", dtoList);
    }

    @GetMapping("/chatroom")
    @ResponseBody
    public void chatByLove(Long roomId, Model model) {
        log.info("chatByLove({})", roomId);

        // roomid로 chatting list를 받아옴
        List<Chatting> chatList = chattingService.getChatListByContentId(roomId);

        model.addAttribute("chatList", chatList);
    }
}


