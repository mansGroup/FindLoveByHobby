package com.fin.love.service.note;

import com.fin.love.repository.note.NoteNumber;
import com.fin.love.repository.note.NoteNumberRepository;
import com.fin.love.repository.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteNumberService {

    private final NoteNumberRepository noteNumberRepository;

    @Transactional
    public void upNoteCount(String userId) {
        NoteNumber entity = noteNumberRepository.findById(userId).orElseGet(NoteNumber::new);
        if (entity.getUserid() == null) {
            noteNumberRepository.save(NoteNumber.builder()
                                        .userid(userId)
                                        .noteCount(1L)
                                        .checkCount(0L)
                                        .build());
        }else {
            entity.upNoteCount();
        }
    }

    public Long getNoteCount(String id) {
        Long noteCount = 0L;

        NoteNumber entity = noteNumberRepository.findById(id).orElseGet(NoteNumber::new);

        if (entity.getUserid() != null) {
            noteCount = entity.getNoteCount() - entity.getCheckCount();
        }
        return noteCount;
    }

    @Transactional
    public boolean checkedNote(String id) {
        NoteNumber number = noteNumberRepository.findById(id).orElseGet(NoteNumber::new);
        if (number.getUserid() == null) {
            return false;
        }

        number.checkedNote(number.getNoteCount());

        return true;
    }
}