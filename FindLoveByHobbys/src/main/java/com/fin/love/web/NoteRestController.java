package com.fin.love.web;

import com.fin.love.repository.note.NoteNumber;
import com.fin.love.service.note.NoteNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/note")
@RestController
public class NoteRestController {

    private final NoteNumberService noteNumberService;
    @GetMapping("/count/{id}")
    public ResponseEntity<Long> getNoteCount(@PathVariable String id) {
        Long count = noteNumberService.getNoteCount(id);
        return ResponseEntity.ok(count);
    }
}
