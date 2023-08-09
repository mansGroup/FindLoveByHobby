package com.fin.love.web;

import com.fin.love.dto.note.NoteContentDto;
import com.fin.love.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("note")
@Controller
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/note")
    public void note(Model model) {
        // TODO 시큐리티 id 바꿔주기
        String id = "user1";
        List<NoteContentDto> noteContentDtos = noteService.getMyNoteContent(id);
        log.info(noteContentDtos.size() + "");
        model.addAttribute("userId", id);
        model.addAttribute("noteContentList", noteContentDtos);
    }
}
