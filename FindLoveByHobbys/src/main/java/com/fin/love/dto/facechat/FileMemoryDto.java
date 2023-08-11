package com.fin.love.dto.facechat;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileMemoryDto {

	// 싱글톤 디자인 패턴 구현.
	// Base64 파일을 value, id를 key로 저장.
	private static FileMemoryDto dto;
	private Map<Long, String> filemap;

	private FileMemoryDto() {

		filemap = new HashMap<Long, String>();

	}

	public static FileMemoryDto getInstance() {

		if (dto == null) {
			dto = new FileMemoryDto();

		}

		return dto;

	}

}
