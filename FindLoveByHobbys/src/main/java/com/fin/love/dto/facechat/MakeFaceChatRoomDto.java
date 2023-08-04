package com.fin.love.dto.facechat;

import com.fin.love.repository.facechat.Speakroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakeFaceChatRoomDto {

	private long roomId;
	private String speakmember1;
	private String speakmember2;
	
	public Speakroom toEntity() {
		
		return Speakroom.builder().roomid(roomId).speakmember1(speakmember1).speakmember2(speakmember2).build();
		
	}
	
}
