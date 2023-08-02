package com.fin.love.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.fin.love.dto.matching.MatchingListDto;

import lombok.Data;

@Data
public class FileUploadDto {
	
	private MultipartFile uploadFile;
	
}
