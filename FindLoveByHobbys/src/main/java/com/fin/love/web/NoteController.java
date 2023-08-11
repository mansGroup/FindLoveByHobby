package com.fin.love.web;

import com.fin.love.dto.note.NoteContentDto;
import com.fin.love.service.note.NoteNumberService;
import com.fin.love.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final NoteNumberService noteNumberService;

    @GetMapping("/note")
    public void note(Model model) {
        // TODO 시큐리티 id 바꿔주기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getName();
        // 쪽지창 들어오면 알람 개수 없애주기
        boolean haveNoteNumber = noteNumberService.checkedNote(userid);

        // NoteNumber 테이블에 해당 아이디가 없으면 아래 문장을 실행할 경우 오류가 발생하기 때문에 조건문은 둠
        if (haveNoteNumber) {
            // 쪽지 내용 불러오기
            List<NoteContentDto> noteContentDtos = noteService.getMyNoteContent(userid);
            log.info(noteContentDtos.size() + "");
            model.addAttribute("noteContentList", noteContentDtos);
        }

    }
}
