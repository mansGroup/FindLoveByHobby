package com.fin.love.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class NoteSocketHandler extends TextWebSocketHandler {

    Set<WebSocketSession> sessionSet = new HashSet<>(); //웹소켓 세션을 담아둘 맵

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //메시지 발송

        // 방번호가 같은 세션들에게 메세지 보내주기 js의 onMessage함수 호출
        for (WebSocketSession s : sessionSet) {
            s.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        sessionSet.add(session);
    }



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        sessionSet.remove(session);
        super.afterConnectionClosed(session, status);
    }

}