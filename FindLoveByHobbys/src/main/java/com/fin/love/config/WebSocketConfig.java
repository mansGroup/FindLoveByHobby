package com.fin.love.config;

import com.fin.love.handler.SocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer{



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getinstance(), "/ws/chat").setAllowedOrigins("*");
        log.info("registerWebSocketHandlers()");
    }

    @Bean
    public SocketHandler getinstance() {

        return new SocketHandler();
    }



}
