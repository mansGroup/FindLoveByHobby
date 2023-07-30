package com.fin.love.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // /chat 엔드포인트로 클라이언트들이 연결할 수 있도록 등록합니다.
        registry.addHandler(new FaceChatWebSocketHandler(), "/facechat").setAllowedOrigins("*");
    }
}