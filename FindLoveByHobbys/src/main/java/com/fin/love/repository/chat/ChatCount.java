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
    private Long maleChatcount;

    @Column
    private Long maleCheckcount;

    @Column
    private Long femaleChatcount;

    @Column
    private Long femaleCheckcount;

    @Column
    private String maleid;

    @Column
    private String femaleid;

    public void upMaleChatCount() {
        ++this.maleChatcount;
    }

    public void upFemaleChatCount() {
        ++this.femaleChatcount;
    }

    public void downMaleChatCount() {
        ++this.maleCheckcount;
    }
    public void downFemaleChatCount() {
        ++this.femaleCheckcount;
    }

    public void maleCheckAllChatCount() {
         this.maleChatcount = 0L;
    }

    public void femaleCheckAllChatCount() {
        this.femaleChatcount = 0L;
    }
}
