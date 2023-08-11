package com.fin.love.dto.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingSearchDto {

	private long locationId;
	private long hobbyId;
	private long ageId;
	
}
