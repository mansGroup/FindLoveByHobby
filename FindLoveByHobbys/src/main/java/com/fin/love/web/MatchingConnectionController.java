package com.fin.love.web;

import com.fin.love.service.LikeService;
import com.fin.love.service.chatting.ChattingRoomService;
import com.fin.love.service.note.NoteNumberService;
import com.fin.love.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/matching")
@Controller
public class MatchingConnectionController {

    private final LikeService likeService;
    private final NoteService noteService;
    private final NoteNumberService noteNumberService;
    private final ChattingRoomService chattingRoomService;

    private String user = "user1";  // TODO 세션연결

    @GetMapping("/profile/{senderId}")
    public String conectionDetail(@PathVariable String senderId) {
        
        return "";
    }

    @GetMapping("/connected/{senderId}")
    public String connected(@PathVariable String senderId) {
        // LIKES 테이블 whether값 0 -> 1
        likeService.chageWhether(senderId, user, 1);

        // 매칭되어 알림이 불필요하여 삭제
        noteService.deleteNote(senderId, user);

        // 채팅방 오픈
        chattingRoomService.makeChattingRoomm(senderId, user);

        // 상대방에게 연결됨을 쪽지로 알림
        noteService.noticeConnected(senderId, user);

        // 상대방 noteCount 올림
        noteNumberService.upNoteCount(senderId);

        return "/chat/chat";
    }

    @GetMapping("/disconnected/{senderId}")
    public String disconnected(@PathVariable String senderId) {
        // LIKES 테이블 whether값 0 -> 2로 바꾸기
        likeService.chageWhether(senderId, user, 2);

        // sender알람 Notecount 올리기
        noteNumberService.upNoteCount(senderId);
        return "";
    }
}
