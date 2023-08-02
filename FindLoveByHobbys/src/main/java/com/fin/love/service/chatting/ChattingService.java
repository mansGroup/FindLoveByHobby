package com.fin.love.service.chatting;

import com.fin.love.repository.chat.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;


}
