package com.fin.love.service.chatting;

import com.fin.love.repository.chat.ChatCount;
import com.fin.love.repository.chat.ChatCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatCountService {

    private final ChatCountRepository chatCountRepository;

    @Transactional
    public void upChatCount(Long roomId, String myId, int mySex) {
        ChatCount chatCount = chatCountRepository.findById(roomId).orElseGet(ChatCount::new);

        if (mySex == 1) {
            if (chatCount.getRoomid() == null) {
                chatCountRepository.save(ChatCount.builder()
                                                .roomid(roomId)
                                                .male_chatcount(1L)
                                                .maleid(myId)
                                                .build());
                return;
            }

            chatCount.upMaleChatCount();

        } else {
            if (chatCount.getRoomid() == null) {
                chatCountRepository.save(ChatCount.builder()
                                                .roomid(roomId)
                                                .female_chatcount(1L)
                                                .femaleid(myId)
                                                .build());
                return;
            }

            chatCount.upFemaleChatCount();
        }
    }

    @Transactional
    public void downChatCount(Long roomId, String myId, int mySex) {
        ChatCount chatCount = chatCountRepository.findById(roomId).orElseGet(ChatCount::new);

        if (mySex == 1) {
            chatCount.downMaleChatCount();
        } else {
            chatCount.downFemaleChatCount();
        }


    }

    public List<ChatCount> getChatCountList(String myId, int mySex) {
        if (mySex == 1) {
            return chatCountRepository.findByMaleid(myId);
        } else {
            return chatCountRepository.findByFemaleid(myId);
        }
    }
}
