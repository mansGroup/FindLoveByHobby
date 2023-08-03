package com.fin.love.dto.chatting;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChattingListDto {
    private String id;
    private String nickname;
    private Long contentId;

    public ChattingListDto(Long contentId, String id) {
        this.contentId = contentId;
        this.id = id;
    }


}
