package com.fin.love.dto;

import com.fin.love.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleAction(WebSocketSession session, ChatDto message, ChatService service) {
        // message에 담긴 타입을 확인한다
        // 이때 message에서 getType으로 가져온 내용이
        // ChatDto의 열거형인 MessageType 안에 있는 ENTER와 동일한 값이라면
        if (message.getType().equals((ChatDto.MessageType.ENTER))) {
            // sessions에 넘어온 session을 담고,
            sessions.add(session);

            // message에는 입장하였나는 메세지를 띄운다
            message.setMessage(message.getSender() + " 님이 입장하셨습니다.");
//            sendMessage(message, service);

        } else if (message.getType().equals(ChatDto.MessageType.TALK)) {
            message.setMessage(message.getMessage());
//            sendMessage(message, service);
        }

//        public <T> void sendMessage(T message, ChatService service) {
//            sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
//        }

    }
}
