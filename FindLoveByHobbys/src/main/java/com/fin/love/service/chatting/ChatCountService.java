package com.fin.love.service.chatting;

import com.fin.love.repository.chat.ChatCount;
import com.fin.love.repository.chat.ChatCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatCountService {

    private final ChatCountRepository chatCountRepository;

    @Transactional
    public void upChatCount(Long roomId, String myId, int mySex) {
        if (mySex == 1) {
            ChatCount chatCount = chatCountRepository.findById(roomId).orElseGet(ChatCount::new);
            if (chatCount.getRoomid() == null) {
                chatCountRepository.save(ChatCount.builder()
                        .roomid(roomId)
                        .male_chatcount(1L)
                        .build());

            }
        }
    }
}
