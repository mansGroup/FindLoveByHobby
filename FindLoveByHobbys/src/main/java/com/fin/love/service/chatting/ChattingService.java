package com.fin.love.service.chatting;

import com.fin.love.repository.chat.Chatting;
import com.fin.love.repository.chat.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;

    public List<Chatting> getChatListByContentId(Long roomId) {
        List<Chatting> list = chattingRepository.findByContentidOrderByChatid(roomId);
        return list;
    }

    public void save(Chatting chatting) {
        chattingRepository.save(chatting);

    }

    @Transactional
    public void deleteChat(Long roomId) {
        chattingRepository.deleteByContentid(roomId);
    }
}
