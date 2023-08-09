package com.fin.love.service.note;

import com.fin.love.dto.note.NoteContentDto;
import com.fin.love.repository.like.Like;
import com.fin.love.repository.like.LikeRepository;
import com.fin.love.repository.note.Note;
import com.fin.love.repository.note.NoteRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;

    public void likeSend(String sender, String recipient) {
        noteRepository.save(Note.builder()
                .sender(sender)
                .recipient(recipient)
                .type("likes")
                .build());
    }

    public List<NoteContentDto> getMyNoteContent(String id) {
        List<NoteContentDto> dtos = new ArrayList<>();
        List<Note> notes = noteRepository.findAll();
        for (Note n : notes) {
            NoteContentDto dto = new NoteContentDto();
            if (n.getType() == "likes") {
                if (n.getSender() == id) {
                    Like like = likeRepository.findBySenderAndRecipient(n.getSender(), n.getRecipient());
                    Member otherMember = memberRepository.findById(n.getRecipient()).orElseThrow();
                    if (like.getWhether() == 0) {
                        dto.setMessage(String.format("%s님에게 좋아요를 보냈어요. 응답을 기다리는 중이에요.",otherMember.getNickname()));
                        dto.setSender("두근두근");
                    } else if (like.getWhether() == 2) {
                        dto.setMessage(String.format("%s님이 거절하셨습니다 ㅠㅠ"));
                        dto.setSender("다음 인연을 만들어요!");
                    }
                } else if (n.getRecipient() == id) {
                    dto.setMessage(String.format("%님이 좋아요를 보냈어요. 인연이 맺어지길 바래요!"));
                    dto.setSender("두근두근");
                    dto.setLink(String.format("/matching/profile/%s", n.getSender()));
                }
            } else {
                dto.setSender("관리자");
                dto.setMessage("문의사항에 답변이 달렸어요!");
                dto.setLink(""); // TODO 문의 응답
            }
            dtos.add(dto);
        }
        return dtos;
    }
}