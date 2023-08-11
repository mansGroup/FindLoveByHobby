package com.fin.love.dto.note;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NoteContentDto {
    private String sender;
    private String message;
    private String link;
}
