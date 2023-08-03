package com.fin.love.dto.image;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.multipart.MultipartFile;

import com.fin.love.dto.matching.MatchingListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
	
	private String fileName;

    private String uuid;

    private String folderPath;

    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/" +uuid + fileName,"UTF-8");

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
	
}
