package com.fin.love.service.note;

import com.fin.love.dto.note.NoteContentDto;
import com.fin.love.repository.like.Like;
import com.fin.love.repository.like.LikeRepository;
import com.fin.love.repository.note.Note;
import com.fin.love.repository.note.NoteRepository;
import com.fin.love.respository.member.Member;
import com.fin.love.respository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.transform.LogASTTransformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        List<Note> notes = noteRepository.findAllByOrderByIdDesc();
        for (Note n : notes) {
            NoteContentDto dto = new NoteContentDto();
            Member senderMemberInfo = memberRepository.findById(n.getSender()).orElseThrow();
            Member recipientMemberInfo = memberRepository.findById(n.getRecipient()).orElseThrow();

            if (n.getType().equals("likes")) {

                if (n.getSender().equals(id)) {
                    log.info("sender?");
                    Like like = likeRepository.findBySenderAndRecipient(n.getSender(), n.getRecipient());
                    log.info(like.getRecipient());

                    if (like.getWhether() == 0) {
                        dto.setMessage(String.format("%s님에게 좋아요를 보냈어요. 응답을 기다리는 중이에요.",recipientMemberInfo.getNickname()));
                        dto.setSender("두근두근");
                        dtos.add(dto);

                    } else if (like.getWhether() == 2) {
                        dto.setMessage(String.format("%s님이 거절하셨습니다 ㅠㅠ", recipientMemberInfo.getNickname()));
                        dto.setSender("다음 인연을 만들어요!");
                        dtos.add(dto);
                    }

                } else if (n.getRecipient().equals(id)) {
                    dto.setMessage(String.format("%s님이 좋아요를 보냈어요. 인연이 맺어지길 바래요!",senderMemberInfo.getNickname()));
                    dto.setSender("두근두근");
                    dto.setLink(String.format("/matching/profile/%s", n.getSender()));
                    dtos.add(dto);
                }

            } else if (n.getType().equals("notice")){
                if (n.getRecipient().equals(id)) {
                    dto.setSender("관리자");
                    dto.setMessage("문의사항에 답변이 달렸어요!");
                     // TODO 문의 응답
                    dtos.add(dto);
                }

            } else if (n.getType().equals("connected")) {
                if (n.getSender().equals(id)) {
                    dto.setSender(String.format("%s님과 매칭되었어요.", recipientMemberInfo.getNickname()));
                    dto.setMessage("어서 채팅하러 가보세요! 쪽지를 누르면 카톡방으로 이동해요.");
                    dto.setLink("/chat/chat");
                    dtos.add(dto);
                }
            }

        }
        return dtos;
    }

    @Transactional
    public void deleteNote(String senderId, String recipientId, String type) {
        noteRepository.deleteBySenderAndRecipientAndType(senderId, recipientId, type);
    }

    public void noticeConnected(String senderId, String recipientId) {
        noteRepository.save(Note.builder()
                .sender(senderId)
                .recipient(recipientId)
                .type("connected")
                .build());
    }
}
