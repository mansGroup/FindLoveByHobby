package com.fin.love.dto.facechat;

import com.fin.love.repository.facechat.SpeakRoom;

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
	
	public SpeakRoom toEntity() {
		
		return SpeakRoom.builder().roomid(roomId).speakmember1(speakmember1).speakmember2(speakmember2).build();
		
	}
	
}
