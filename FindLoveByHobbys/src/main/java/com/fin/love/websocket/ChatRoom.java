package com.fin.love.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Data
@Slf4j
public class ChatRoom {
	
	private String roomId;
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	public void addSession(WebSocketSession session) {
	        sessions.add(session);
	}

	public void removeSession(WebSocketSession session) {
	        sessions.remove(session);
	}
	
	public Set<WebSocketSession> getSessions() {
        return sessions;
    }
	
}
