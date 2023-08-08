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

    HashMap<String, Set<WebSocketSession>> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //메시지 발송

        // 세션에서 방번호 가져오기
        String id = getId(session);

        // 방번호가 같은 세션들 찾아오기(같은 방에 속한 세션들)
        Set<WebSocketSession> sessions = sessionMap.get(id);
        log.info(sessions.size()+"세션 사이즈");

        // 방번호가 같은 세션들에게 메세지 보내주기 js의 onMessage함수 호출
        for (WebSocketSession s : sessions) {
            s.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        log.info(session.getId()+"=====================");

        // 세션에서 방번호 찾아오기
        String id = getId(session);

        // 세션맵에 roomID가 있으면 불러와 해당 id에 세션을 저장
        // 없으면 새로운 key로 roomID를 넣고 세션을 넣은 해쉬맵 생성
        if (sessionMap.get(id) != null) {
            Set<WebSocketSession> set =  sessionMap.get(id);
            set.add(session);
            sessionMap.put(id, set);
        } else {
            Set<WebSocketSession> set = new HashSet<>();
            set.add(session);
            sessionMap.put(id,set);
        }
        log.info("세션 사이즈 연결확립메서드 동작"+sessionMap.size());

    }

    private String getId(WebSocketSession session) {
        String path = session.getUri().getQuery().toString();
        log.info("path = " + path);
        String[] arr = path.split("=");
        log.info(arr[1]);
        return arr[1];
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

}