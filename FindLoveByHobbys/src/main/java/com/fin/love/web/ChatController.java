package com.fin.love.web;

import com.fin.love.dto.chatting.ChattingListDto;
import com.fin.love.repository.chat.Chatting;
import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.chatting.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
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
        String userId = "user1";

        // 나의 성별 찾기
        Member member = memberService.getSexById(userId);

        // id로 상대방 id, 채팅방 정보 가져오기
        List<ChattingListDto> dtoList = chattingRoomService.getChattingRoomListById(userId, member.getSex());

        // 상대방 id로 닉네임 리스트 가져오기
        for (ChattingListDto dto : dtoList) {
            dto.setNickname(memberService.getNicknameById(dto.getId()));
        }

        // 모델에 리스트 실어주기
        model.addAttribute("dtoList", dtoList);
        log.info("채팅방 정보 리스트 사이즈 from chatController {}", dtoList.size());
    }

    @GetMapping("/chatroom/{room}")
    public String chatByLove(@PathVariable Long room, Model model) {
        log.info("chatByLove({})", room);

        // TODO spring security session 적용
        // 세션에서 id 찾아오기
        String userId = "user1";

        // 나의 성별 찾기
        Member member = memberService.getSexById(userId);

        // userId로 채팅방번호 상대방 ID 리스트 가져오기
        List<ChattingListDto> dtoList = chattingRoomService.getChattingRoomListById(userId, member.getSex());

        // 상대방 ID로 상대방 nickname 가져오기
        for (ChattingListDto dto : dtoList) {
            dto.setNickname(memberService.getNicknameById(dto.getId()));
        }

        // roomid로 chatting list를 받아옴
        List<Chatting> chatList = chattingService.getChatListByContentId(room);
        log.info("chatList size({})", chatList.size());

        // 내 id로 내 nickname 찾아오기
        String myNickname = memberService.getNicknameById(userId);

        // 내 id로 내 nickname 찾아오기
        String otherNickname = "";
        for (Chatting x : chatList) {
            if (x.getNickname() != myNickname) {
                otherNickname = x.getNickname();
                break;
            }
        }

        // 닉네임과 채팅방 번호 모델에 리스트 실어주기
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("chatList", chatList);
        model.addAttribute("roomId", room);
        model.addAttribute("myNickname", myNickname);
        model.addAttribute("otherNickname", otherNickname);
        return "/chat/chatroom";
    }
}


