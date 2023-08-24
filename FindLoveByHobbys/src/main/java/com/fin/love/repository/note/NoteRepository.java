package com.fin.love.repository.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    void deleteBySenderAndRecipient(String sender, String recipient);

    List<Note> findAllByOrderByIdDesc();

    void deleteBySenderAndRecipientAndType(String senderId, String recipientId, String type);
}
