package com.fin.love.repository.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHATCOUNT")
@Entity
public class ChatCount {

    @Id
    private Long roomid;

    @Column
    private Long male_chatcount;

    @Column
    private Long male_checkcount;

    @Column
    private Long female_chatcount;

    @Column
    private Long female_checkcount;
}
