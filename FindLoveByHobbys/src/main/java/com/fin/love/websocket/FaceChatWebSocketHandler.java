package com.fin.love.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FaceChatWebSocketHandler implements WebSocketHandler {
	
	private Map<String, ChatRoom> sessions = new HashMap<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		String roomId = findRoomId(session);
		log.info("session on");
		if(roomId != null) {
			
			if(sessions.get(roomId)==null) {
				
				sessions.put(roomId, new ChatRoom());
				
			} 
			
			sessions.get(roomId).addSession(session);
			
			
			
		}
		
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		String roomId = findRoomId(session);
		
		if(sessions.get(roomId)==null) {
			
			return;
			
		}
		
		Set<WebSocketSession> ss = sessions.get(roomId).getSessions();
		
		for(WebSocketSession x : ss) {
			
			if(!x.getId().equals(session.getId())) {
				log.info("x.getid = {}, session.getid= {}",x.getId(),session.getId());
				
				
				
				x.sendMessage(message);
				
				
			}
				
			
			
			
		}
		
	}

	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub

		removeSession(session);
		
	}

	
	public void removeSession(WebSocketSession session) {
		log.info("session out");
		String roomId = findRoomId(session);
        if (roomId != null) {
        	
            if (sessions.get(roomId) != null || sessions.size()!=0) {
            	
            	sessions.get(roomId).removeSession(session);
            	
            	
            }
        }
		
	}
	
	
	private String findRoomId(WebSocketSession session) {
        
        String roomId = session.getUri().getQuery().toString().split("=")[1];
        
        return roomId;
    }

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		log.info("Socket Link Error");
		
		removeSession(session);
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
