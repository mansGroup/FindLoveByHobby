package com.fin.love.web;

import com.fin.love.dto.chatting.ChattingListDto;
import com.fin.love.repository.chat.Chatting;
import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.respository.member.Member;
import com.fin.love.service.MemberService;
import com.fin.love.service.chatting.ChatCountService;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.chatting.ChattingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final ChatCountService chatCountService;

    @GetMapping("/demo")
    public void demo() {

    }

    @GetMapping("/chat")
    public void chat(Model model) {

        log.info("chat()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        model.addAttribute("id", userid);

        // 나의 성별 찾기
        Member member = memberService.getSexById(userid);
        model.addAttribute("mySex", member.getSex());


        // id로 상대방 id, 채팅방 정보 가져오기
        List<ChattingListDto> dtoList = chattingRoomService.getChattingRoomListById(userid, member.getSex());

        // 상대방 id로 닉네임 리스트 가져오기
        for (ChattingListDto dto : dtoList) {
            dto.setNickname(memberService.getNicknameById(dto.getId()));
        }
        model.addAttribute("dtoList", dtoList);

        log.info("채팅방 정보 리스트 사이즈 from chatController {}", dtoList.size());
    }

    @GetMapping("/chatroom/{room}")
    public String chatByLove(@PathVariable Long room, Model model) {
        log.info("chatByLove({})", room);
        model.addAttribute("roomId", room);

        // spring security에서 id 찾아오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        model.addAttribute("myId", userid);

        // 나의 성별 찾기
        Member member = memberService.getSexById(userid);
        model.addAttribute("mySex", member.getSex());

        // 채팅방 알람 초기화
        chattingService.checkAllChatCount(room, userid, member.getSex());

        // 성별에 따라 maleid, femaleid 넣어주기
        String maleID = "";
        String femaleId = "";
        if (member.getSex() == 1) {
            maleID = userid;
        } else {
            femaleId = userid;
        }

        // userId로 채팅방번호, 상대방 ID 리스트 가져오기
        List<ChattingListDto> dtoList = chattingRoomService.getChattingRoomListById(userid, member.getSex());
        model.addAttribute("dtoList", dtoList);

        // 상대방 ID로 상대방 nickname 가져오기
        for (ChattingListDto dto : dtoList) {
            dto.setNickname(memberService.getNicknameById(dto.getId()));
            if (dto.getContentId() == room) {
                if (maleID == "") {
                    maleID = dto.getId();
                } else {
                    femaleId = dto.getId();
                }
            }
        }
        model.addAttribute("maleId", maleID);
        model.addAttribute("femaleId", femaleId);


        // roomid로 chatting list를 받아옴
        List<Chatting> chatList = chattingService.getChatListByContentId(room);
        log.info("chatList size({})", chatList.size());
        model.addAttribute("chatList", chatList);

        // 내 id로 내 nickname 찾아오기
        String myNickname = memberService.getNicknameById(userid);
        model.addAttribute("myNickname", myNickname);

        // 상대방 id로 상대방 nickname 찾아오기
        String otherNickname = "";
        if (maleID == userid) {
            Member otherInfo = memberService.getMemberInfo(femaleId);
            otherNickname = otherInfo.getNickname();
        } else {
            Member otherInfo = memberService.getMemberInfo(maleID);
            otherNickname = otherInfo.getNickname();
        }
        model.addAttribute("otherNickname", otherNickname);

        return "/chat/chatroom";
    }

    @Transactional
    @PostMapping("/out")
    public String chatOut(Long roomId) {
        log.info("chatOut({})", roomId);

        chattingService.deleteChat(roomId);
        chattingRoomService.deleteRoom(roomId);

        return "redirect:/chat/demo";
    }
}