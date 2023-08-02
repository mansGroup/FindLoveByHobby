package com.fin.love.service.chatting;

import com.fin.love.repository.chat.Chatting;
import com.fin.love.repository.chat.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;


    public List<Chatting> getChatListByContentId(Long roomId) {
        List<Chatting> list = (List<Chatting>) chattingRepository.findById(roomId).orElseThrow();
        return list;
    }
}
