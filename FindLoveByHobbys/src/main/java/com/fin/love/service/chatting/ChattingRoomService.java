package com.fin.love.service.chatting;

import com.fin.love.dto.chatting.ChattingListDto;
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
    public List<ChattingListDto> getChattingRoomListById(String userId) {
        // userId에 해당하는 채팅방 정보 가져오기
        List<ChattingRoom> list = chattingRoomRepository.findByMyId(userId);

        //
        List<ChattingListDto> dtoList = new ArrayList<>();

        // 채팅방 정보에서 상대방 ID, 상대방과 연결된 채팅방 번호 가져오기
        for (ChattingRoom entity : list) {
            ChattingListDto dto = new ChattingListDto(entity.getContentid(), entity.getOtherId());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
