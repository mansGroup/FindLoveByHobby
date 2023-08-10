package com.fin.love.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    void deleteBySenderAndRecipient(String sender, String recipient);
}
