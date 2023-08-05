package com.fin.love.dto.facechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportListDto {

	private long check;
	private long updatenum;
	private int status;
	
}
