package com.fin.love.repository.chat;

import com.fin.love.repository.BaseCreatedTimeEntity;
import com.fin.love.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@SequenceGenerator(name = "CHAT_SEQ_GEN", sequenceName = "CHAT_SEQ", allocationSize = 1)
@Table(name = "CHAT")
@Entity
public class Chat extends BaseCreatedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ_GEN")
    private Long chatid;

    @Column
    private Long contentid;

    @Column
    private String conversation;

    @Column
    private Long texttype;
}
