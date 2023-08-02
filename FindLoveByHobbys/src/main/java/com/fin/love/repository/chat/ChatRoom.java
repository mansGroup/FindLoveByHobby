package com.fin.love.repository.chat;

import com.fin.love.repository.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "CHATROOM_SEQ_GEN", sequenceName = "CHATROOM_SEQ", allocationSize = 1)
@Table(name = "CHATROOM")
@Entity
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATROOM_SEQ_GEN")
    private Long contentid;

    @Column
    private String maleid;

    @Column
    private String femaleid;
}
