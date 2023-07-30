package com.fin.love.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SocketHandler extends TextWebSocketHandler {


    HashMap<String, WebSocketSession> sesseionMap = new HashMap<>();
        // 웹소켓 세션을 담아둘 맵

    /**
     * 메세지가 수신되면 실행
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메세지가 수신되면 실행
        String msg = message.getPayload();
        for (String key : sesseionMap.keySet()) {
            WebSocketSession wss = sesseionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 웹소켓 연결이 확립된 후 실행
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        super.afterConnectionEstablished(session);
    }


    /**
     * 웹소켓 연결이 종료된 후 실행
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
