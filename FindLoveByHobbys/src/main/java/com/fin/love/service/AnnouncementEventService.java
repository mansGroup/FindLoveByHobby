package com.fin.love.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fin.love.dto.announcementEvent.AnnouncementEventDto;
import com.fin.love.repository.announcementEvent.AnnouncementEvent;
import com.fin.love.repository.announcementEvent.AnnouncementEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnnouncementEventService {

	private final AnnouncementEventRepository announcementEventRepository;

	// DB POSTS 테이블에서 전체 검색한 결과를 리턴:
	@Transactional(readOnly = true)
	public List<AnnouncementEvent> read() {
		log.info("read()");

		return announcementEventRepository.findByOrderByIdDesc();
	}

	public AnnouncementEvent create(AnnouncementEventDto dto) {
		log.info("create(dto={})", dto);

		// DTO를 Entity객체로 변환:
		AnnouncementEvent entity = dto.toEntity();
		log.info("entity={}", entity);

		// DB 테이블에 저장(insert)
		announcementEventRepository.save(entity);
		log.info("entity={}", entity);

		return entity;

	}
	
    @Transactional(readOnly = true)
    public AnnouncementEvent read(Long id) {
        log.info("read(id={})", id);
        
        return announcementEventRepository.findById(id).orElseThrow();
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
    
}
