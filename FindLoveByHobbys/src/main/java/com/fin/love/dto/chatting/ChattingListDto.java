package com.fin.love.dto.chatting;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChattingListDto {
    private String nickname;
    private Long contentId;

    public ChattingListDto(Long contentId) {
        this.contentId = contentId;
    }
}
