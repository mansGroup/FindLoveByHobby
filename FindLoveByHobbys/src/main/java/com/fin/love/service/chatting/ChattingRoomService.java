package com.fin.love.service.chatting;

import com.fin.love.repository.chat.ChattingRoom;
import com.fin.love.repository.chat.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;
    public List<ChattingRoom> getChattingRoomListById(String session) {
        List<ChattingRoom> list = chattingRoomRepository.findByMyId(session);
        return list;
    }
}
