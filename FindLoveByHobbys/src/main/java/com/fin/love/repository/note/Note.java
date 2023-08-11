package com.fin.love.repository.note;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "NOTE_SEQ_GEN", sequenceName = "NOTE_SEQ", allocationSize = 1)
@Table(name = "NOTE")
@Entity
public class Note {

    @Id
    @GeneratedValue(generator = "NOTE_SEQ_GEN", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String sender;

    @Column
    private String recipient;

    @Column
    private String type;

}
