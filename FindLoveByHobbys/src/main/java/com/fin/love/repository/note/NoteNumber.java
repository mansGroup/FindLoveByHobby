package com.fin.love.repository.note;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "NOTENUMBER")
@Entity
public class NoteNumber {

    @Id
    private String userid;

    @Column
    private Long noteCount;

    @Column
    private Long checkCount;

    public NoteNumber upNoteCount(Long noteCount) {
        this.noteCount++;
        return this;
    }

    public void checkedNote(Long noteNumber) {
        this.checkCount = noteNumber;
    }
}
