package com.fin.love.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.announcementEvent.AnnouncementEventCreateDto;
import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.repository.announcementEvent.AnnouncementEventPicture;
import com.fin.love.repository.announcementEvent.AnnouncementEventPictureRepository;
import com.fin.love.repository.announcementEvent.AnnouncementEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnnouncementEventService {
	
	@Value("${com.example.upload.path}") // application.properties의 변수
	private String eventUploadPath;
	
	private final AnnouncementEventRepository announcementEventRepository;
	private final AnnouncementEventPictureRepository announcementEventPictureRepository;
	
//=================================이미지 관련========================================================================================

	// 날짜별로 폴더 생성하는 메서드.
		public String makeFolder() {
			log.info("makeForder()");

			String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

			String folderPath = str.replace("/", File.separator);

			// make folder ----
			File uploadPatheFolder = new File(eventUploadPath, folderPath);

			if (uploadPatheFolder.exists() == false) {
				uploadPatheFolder.mkdirs();
			}

			return folderPath;
		}

		// 업로드시 수정하는 메서드
		@Transactional
		public void pic1SaveImage(Long id, String saveImagePathName, String name) {
			log.info("saveImage(UserId = {})", id);
			log.info("path >>>>> " + saveImagePathName);
			
			AnnouncementEventPicture pic = AnnouncementEventPicture.builder().picture(saveImagePathName).id(id).name(name).build();
			log.info("pic >>>>> " + pic);
			
			announcementEventPictureRepository.save(pic);
		}
		
		@Transactional
		public void pic1SaveImage(Long id, String saveImagePathName) {
			log.info("saveImage(UserId = {})", id);
			log.info("path >>>>> " + saveImagePathName);
			
			AnnouncementEventPicture pic = announcementEventPictureRepository.findById(id).orElseThrow();
			log.info("pic >>>>> " + pic);
			
			pic.picUpdate(saveImagePathName);
		}
//=================================이미지 관련========================================================================================
	
		// DB ANNOUNCEMENT_EVENT 테이블에서 전체 검색한 결과를 리턴:
	@Transactional(readOnly = true)
	public List<AnnouncementEvent> read() {
		log.info("read()");

		return announcementEventRepository.findByOrderByIdDesc();
	}

	public AnnouncementEvent create(AnnouncementEventCreateDto dto) {
		log.info("create(dto={})", dto);

		// DTO를 Entity객체로 변환:
		AnnouncementEvent entity = dto.toEntity();
		log.info("entity={}", entity);

		// DB 테이블에 저장(insert)
		AnnouncementEvent result = announcementEventRepository.save(entity);
		log.info("result={}", result);

		return result;

	}
	
	@Transactional(readOnly = true)
	public AnnouncementEvent read(Long id) {
	    log.info("read(id={})", id);
	    
	    AnnouncementEvent event = announcementEventRepository.findById(id).orElseThrow();

	    return event;
	}
    
    
    public void delete(Long id) {
        log.info("delete(id={})", id);
        
        announcementEventRepository.deleteById(id);
    }
    
    @Transactional // (1)
    public void update(AnnouncementEventDto dto) {
        log.info("update(dto={})", dto);
        
        // (1) 메서드에 @Transactional 애너테이션을 설정하고,
        // (2) DB에서 엔터티를 검색하고, 
        // (3) 검색한 엔터티를 수정하면,
        // 트랙잭션이 끝나는 시점에 DB update가 자동으로 수행됨!
        AnnouncementEvent entity = announcementEventRepository.findById(dto.getId()).orElseThrow(); // (2)
        entity.update(dto); // (3)
        
    }

	public List<AnnouncementEvent> findAll() {
		log.info("findAll()");

		return announcementEventRepository.findAll();
	}

	
	public AnnouncementEventPicture findByid(Long id) {
		
		AnnouncementEventPicture anep = announcementEventPictureRepository.findById(id).orElse(null);
		
		return anep;
	}
	
	// 출력하는 사진으로 변경시키기
		public String imageChange(String picture) {
			log.info("imageChange(picture = {})", picture);

			if (picture.equals("/images/Adding_a_Person_Image.png")) {
				return picture;
			}

			String result = "/images/uploadImages/";
			result += picture;

			return result;
		}
    
}
