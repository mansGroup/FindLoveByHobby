package com.fin.love.service.chatting;

import com.fin.love.repository.chat.ChatCount;
import com.fin.love.repository.chat.ChatCountRepository;
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
    private final ChatCountRepository chatCountRepository;

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

    @Transactional
    public void checkAllChatCount(Long room, String maleID, int sex) {
        ChatCount chatCount = chatCountRepository.findById(room).orElseGet(ChatCount::new);
        if (chatCount.getRoomid() != null) {
            if (sex == 1) {
                chatCount.maleCheckAllChatCount();
            } else {
                chatCount.femaleCheckAllChatCount();
            }
        }
    }
}
